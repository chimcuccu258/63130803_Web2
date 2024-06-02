import React, { useState, useEffect } from "react";
import { Modal, Button, Table, Tag } from "antd";
import axios from "axios";
import { formatDateTime } from "../../../utils/formatDateTime";

interface OrderInfoProps {
  orderId: number;
}

const OrderInformation: React.FC<OrderInfoProps> = ({ orderId }) => {
  const [orderData, setOrderData] = useState<any>(null);
  const [visible, setVisible] = useState(false);

  const fetchOrderData = async () => {
    try {
      const token = localStorage.getItem("token");
      if (!token) {
        throw new Error("No token found");
      }
  
      const response = await axios.get(
        `http://localhost:8080/admin/v1/manage-order/find-by-id?orderID=${orderId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      const { data } = response.data;
      setOrderData(data);
    } catch (error) {
      console.error("Error fetching order data:", error);
    }
  };
  

  useEffect(() => {
    if (orderId) {
      fetchOrderData();
    }
  }, [orderId]);

  const showModal = () => {
    setVisible(true);
  };

  const handleCancel = () => {
    setVisible(false);
  };

  const columns = [
    {
      title: "Field",
      dataIndex: "field",
      key: "field",
      width: "100px",
    },
    {
      title: "Value",
      dataIndex: "value",
      key: "value",
    },
  ];

  const data = orderData
    ? [
        { key: "Order ID", field: "Order ID", value: orderData.orderID },
        
        { key: "Origin", field: "Origin", value: orderData.origin },
        { key: "Destination", field: "Destination", value: orderData.destination },
        { key: "Start Shipping Time", field: "Start Shipping Time", value: formatDateTime(orderData.startShippingTime) },
        { key: "Estimated Time", field: "Estimated Time", value: formatDateTime(orderData.estimatedTime) },
        { key: "Fee", field: "Fee", value: orderData.fee },
        { key: "Order Status", field: "Order Status", value: orderData.orderStatus },
        { key: "Paying Status", field: "Paying Status", value: orderData.payingStatus },
        { key: "Created At", field: "Created At", value: formatDateTime(orderData.created_at) },
        { key: "Updated At", field: "Updated At", value: formatDateTime(orderData.updated_at) },
      ]
    : [];

  return (
    <>
      <a onClick={showModal}>Information</a>
      <Modal
        title="Order Information"
        centered
        visible={visible}
        onCancel={handleCancel}
        footer={[
          <Button key="close" onClick={handleCancel}>
            Close
          </Button>,
        ]}
      >
        <Table columns={columns} dataSource={data} pagination={false} showHeader={false} />
      </Modal>
    </>
  );
};

export default OrderInformation;
