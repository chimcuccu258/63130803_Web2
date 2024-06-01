import React, { useState } from "react";
import {
  Modal,
  Form,
  Input,
  Button,
  DatePicker,
  Radio,
  InputNumber,
} from "antd";

interface CreateOrderProps {
  open: boolean;
  onCancel: () => void;
  onAdd: () => void;
}

const CreateOrder: React.FC<CreateOrderProps> = ({ open, onCancel, onAdd }) => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);

  const onSubmit = async (values: any) => {
    setLoading(true);
    try {
      const token = localStorage.getItem("token"); // Retrieve token from localStorage
      if (!token) {
        throw new Error("No token found");
      }

      const response = await fetch(
        "http://localhost:8080/admin/v1/manage-order/create-order",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`, // Include the token in the request headers
          },
          body: JSON.stringify({
            customerFullName: values.customerFullName,
            customerPhoneNumber: values.customerPhoneNumber,
            supplerID: values.supplierID,
            addressFrom: values.addressFrom,
            addressTo: values.addressTo,
            startShippingTime: values.startShippingTime.format(),
            estimatedTime: values.estimatedTime.format(),
            fee: values.fee,
            payingStatus: values.payingStatus,
            orderDetails: [], // Add order details here if available
          }),
        }
      );

      if (!response.ok) {
        throw new Error("Network response was not ok");
      }

      form.resetFields();
      onAdd();
    } catch (error) {
      console.error("Error creating order:", error);
    } finally {
      setLoading(false);
      onCancel();
    }
  };

  return (
    <Modal
      title="Create Order"
      centered
      open={open}
      onCancel={onCancel}
      footer={null}
    >
      <Form form={form} layout="vertical" onFinish={onSubmit}>
        <Form.Item
          name="customerFullName"
          label="Customer Full Name"
          rules={[
            {
              required: true,
              message: "Please input the customer's full name!",
            },
          ]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="customerPhoneNumber"
          label="Customer Phone Number"
          rules={[
            {
              required: true,
              message: "Please input the customer's phone number!",
            },
          ]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="supplierID"
          label="Supplier ID"
          rules={[{ required: true, message: "Please input the supplier ID!" }]}
        >
          <InputNumber min={1} />
        </Form.Item>
        <Form.Item
          name="addressFrom"
          label="Address From"
          rules={[
            { required: true, message: "Please input the origin address!" },
          ]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="addressTo"
          label="Address To"
          rules={[
            {
              required: true,
              message: "Please input the destination address!",
            },
          ]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="startShippingTime"
          label="Start Shipping Time"
          rules={[
            {
              required: true,
              message: "Please select the start shipping time!",
            },
          ]}
        >
          <DatePicker showTime />
        </Form.Item>
        <Form.Item
          name="estimatedTime"
          label="Estimated Time"
          rules={[
            { required: true, message: "Please select the estimated time!" },
          ]}
        >
          <DatePicker showTime />
        </Form.Item>
        <Form.Item
          name="fee"
          label="Fee"
          rules={[{ required: true, message: "Please input the fee!" }]}
        >
          <InputNumber min={0} />
        </Form.Item>
        <Form.Item
          name="payingStatus"
          label="Paying Status"
          rules={[
            { required: true, message: "Please select the paying status!" },
          ]}
        >
          <Radio.Group>
            <Radio value="PAID">Paid</Radio>
            <Radio value="UNPAID">Unpaid</Radio>
          </Radio.Group>
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit" loading={loading}>
            Create Order
          </Button>
          <Button type="text" onClick={onCancel}>
            Cancel
          </Button>
        </Form.Item>
      </Form>
    </Modal>
  );
};

export default CreateOrder;
