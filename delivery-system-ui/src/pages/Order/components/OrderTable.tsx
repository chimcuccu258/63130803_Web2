import React, { useEffect, useState } from "react";
import { Button, Select, Space, Table, Tag, message } from "antd";
import axios from "axios";
import { ColumnsType } from "antd/es/table";
import { formatDateTime } from "../../../utils/formatDateTime";
import CreateOrder from "./CreateOrder";
import OrderInformation from "./OrderInformation";

// Define types for the order data
interface OrderType {
  orderID: number;
  customerID: number;
  supplierID: number;
  employeeID: number;
  origin: string;
  destination: string;
  startShippingTime: string;
  estimatedTime: string;
  fee: number;
  orderStatus: "DELIVERING" | "DELIVERED" | "CANCELLED" | "PENDING";
  payingStatus: "PAID" | "UNPAID";
  created_at: string;
  updated_at: string;
}

const { Option } = Select;

const OrderTable: React.FC = () => {
  const [data, setData] = useState<OrderType[]>([]);
  const [createOrderModalVisible, setCreateOrderModalVisible] = useState<boolean>(false);
  const [selectedOrderId, setSelectedOrderId] = useState<number | null>(null);

  const fetchData = async () => {
    try {
      const token = localStorage.getItem("token"); 
      if (!token) {
        throw new Error("No token found");
      }

      const response = await axios.post<{
        status: string;
        message: string;
        data: OrderType[];
      }>(
        "http://localhost:8080/admin/v1/manage-order/all",
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`, 
          },
        }
      );
      setData(response.data.data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleStatusChange = async (orderID: number, orderStatus: OrderType["orderStatus"]) => {
    try {
      const token = localStorage.getItem("token");
      if (!token) {
        throw new Error("No token found");
      }
  
      console.log("Updating order status:", orderID, orderStatus);
  
      await axios.post(
        `http://localhost:8080/admin/v1/manage-order/update-status?orderStatus=${orderStatus}&orderID=${orderID}`,
        {}, // Remove the data object here
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );      
      message.success("Order status updated successfully");
      fetchData(); 
    } catch (error) {
      console.error("Error updating order status:", error);
      message.error("Failed to update order status");
    }
  };

  const rowSelection = {
    onChange: (selectedRowKeys: React.Key[], selectedRows: OrderType[]) => {
      if (selectedRows.length > 0) {
        setSelectedOrderId(selectedRows[0].orderID);
      } else {
        setSelectedOrderId(null);
      }
    },
  };
  

  const columns: ColumnsType<OrderType> = [
    {
      title: "Order ID",
      dataIndex: "orderID",
      key: "orderID",
    },
    {
      title: "Origin",
      dataIndex: "origin",
      key: "origin",
    },
    {
      title: "Destination",
      dataIndex: "destination",
      key: "destination",
    },
    {
      title: "Start Shipping Time",
      dataIndex: "startShippingTime",
      key: "startShippingTime",
      render: (startShippingTime: string) => formatDateTime(startShippingTime),
    },
    {
      title: "Estimated Time",
      dataIndex: "estimatedTime",
      key: "estimatedTime",
      render: (estimatedTime: string) => formatDateTime(estimatedTime),
    },
    {
      title: "Fee",
      dataIndex: "fee",
      key: "fee",
    },
    {
      title: "Order Status",
      dataIndex: "orderStatus",
      key: "orderStatus",
      render: (orderStatus: OrderType["orderStatus"], record: OrderType) => {
        let color;
        switch (orderStatus) {
          case "DELIVERING":
            color = "blue";
            break;
          case "DELIVERED":
            color = "green";
            break;
          case "CANCELLED":
            color = "red";
            break;
          case "PENDING":
            color = "orange";
            break;
          default:
            color = "gray";
        }
        return (
          <Select
            defaultValue={orderStatus}
            onChange={(value: OrderType["orderStatus"]) => handleStatusChange(record.orderID, value)}
            style={{ width: 140 }}
          >
            <Option value="DELIVERING">
              <Tag color="blue">DELIVERING</Tag>
            </Option>
            <Option value="DELIVERED">
              <Tag color="green">DELIVERED</Tag>
            </Option>
            <Option value="CANCELLED">
              <Tag color="red">CANCELLED</Tag>
            </Option>
            <Option value="PENDING">
              <Tag color="orange">PENDING</Tag>
            </Option>
          </Select>
        );
      },
    },
    {
      title: "Paying Status",
      dataIndex: "payingStatus",
      key: "payingStatus",
      render: (payingStatus: OrderType["payingStatus"]) => (
        <Tag color={payingStatus === "PAID" ? "blue" : "red"}>
          {payingStatus.toUpperCase()}
        </Tag>
      ),
    },
    {
      title: "Created At",
      dataIndex: "created_at",
      key: "createdAt",
      render: (createdAt: string) => formatDateTime(createdAt),
    },
    {
      title: "Updated At",
      dataIndex: "updated_at",
      key: "updatedAt",
      render: (updatedAt: string) => formatDateTime(updatedAt),
    },
    {
      title: "Action",
      key: "action",
      render: (_: any, order: OrderType) => (
        <Space size="middle">
          <OrderInformation orderId={1} />
        </Space>
      ),
    },
  ];

  return (
    <div>
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
        <h3>Orders</h3>
        <Button type="primary" onClick={() => setCreateOrderModalVisible(true)}>
          + Add new order
        </Button>
      </div>
      <Table columns={columns} dataSource={data} rowKey="orderID" />
      {selectedOrderId && <OrderInformation orderId={selectedOrderId} />}
      <CreateOrder
        open={createOrderModalVisible}
        onCancel={() => setCreateOrderModalVisible(false)}
        onAdd={() => {
          setCreateOrderModalVisible(false);
          fetchData(); 
        }}
      />
    </div>
  );
};

export default OrderTable;
