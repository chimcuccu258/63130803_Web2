import React, { useEffect, useState } from "react";
import { Button, Flex, Table, Tag } from "antd";
import axios from "axios";
import { ColumnsType } from "antd/es/table";
import { formatDateTime } from "../../../utils/formatDateTime";
import CreateOrder from "./CreateOrder";

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
    render: (orderStatus: OrderType["orderStatus"]) => {
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
      return <Tag color={color}>{orderStatus.toUpperCase()}</Tag>;
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
];

const OrderTable: React.FC = () => {
  const [data, setData] = useState<OrderType[]>([]);
  const [createOrderModalVisible, setCreateOrderModalVisible] = useState<boolean>(false);

  const fetchData = async () => {
    try {
      const token = localStorage.getItem("token"); // Retrieve token from localStorage
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
            Authorization: `Bearer ${token}`, // Include the token in the request headers
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

  return (
    <div>
      <Flex justify="space-between" align="center">
        <h3>Orders</h3>
        <Button type="primary" onClick={() => setCreateOrderModalVisible(true)}>
          + Add new order
        </Button>
      </Flex>
      <Table columns={columns} dataSource={data} rowKey="orderID" />
      <CreateOrder
        open={createOrderModalVisible}
        onCancel={() => setCreateOrderModalVisible(false)}
        onAdd={() => {
          setCreateOrderModalVisible(false);
          fetchData(); // Re-fetch the order data after a new order is added
        }}
      />
    </div>
  );
};

export default OrderTable;
