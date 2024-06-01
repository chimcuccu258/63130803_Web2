import React, { useState } from "react";
import { Modal, Form, Input, Button, Radio, message } from "antd";

interface AddSupplierProps {
  open: boolean;
  onCancel: () => void;
  onAdd: () => void;
}

const EmailInput: React.FC<{
  value?: string;
  onChange?: (value: string) => void;
}> = ({ value = "", onChange }) => {
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const emailValue = e.target.value;
    if (!emailValue.includes("@")) {
      onChange?.(emailValue);
    }
  };

  return (
    <Input
      value={value}
      onChange={handleChange}
      addonAfter="@dls.su.com"
      placeholder="Enter email prefix"
    />
  );
};

const AddSupplier: React.FC<AddSupplierProps> = ({ open, onCancel, onAdd }) => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);

  const onSubmit = async (values: any) => {
    setLoading(true);
    try {
      const response = await fetch(
        "http://localhost:8080/auth/supplier-signup",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            ...values,
            email: values.email + "@dls.su.com",
          }),
        }
      );

      if (!response.ok) {
        throw new Error("Network response was not ok");
      }

      form.resetFields();
      onAdd();
      message.success("Supplier added successfully!");
    } catch (error) {
      console.error("Error adding supplier:", error);
      message.error("Failed to add supplier. Please try again later.");
    } finally {
      setLoading(false);
      onCancel();
    }
  };

  return (
    <Modal
      title="Add Supplier"
      centered
      visible={open}
      onCancel={onCancel}
      footer={null}
    >
      <Form form={form} layout="vertical" onFinish={onSubmit}>
        <Form.Item
          name="supplierName"
          label="Supplier Name"
          rules={[{ required: true, message: "Please input the supplier name!" }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="supplierStatus"
          label="Supplier Status"
          rules={[{ required: true, message: "Please select the supplier status!" }]}
        >
          <Radio.Group>
            <Radio value={true}>Open</Radio>
            <Radio value={false}>Closed</Radio>
          </Radio.Group>
        </Form.Item>
        <Form.Item
          name="address"
          label="Address"
          rules={[{ required: true, message: "Please input the address!" }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="phoneNumber"
          label="Phone Number"
          rules={[
            { required: true, message: "Please input the phone number!" },
          ]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="email"
          label="Email"
          rules={[{ required: true, message: "Please input the email!" }]}
        >
          <EmailInput />
        </Form.Item>
        <Form.Item
          name="password"
          label="Password"
          rules={[{ required: true, message: "Please input the password!" }]}
        >
          <Input.Password />
        </Form.Item>
        <Form.Item>
          <Button type="primary" htmlType="submit" loading={loading}>
            Add Supplier
          </Button>
          <Button type="text" onClick={onCancel}>
            Cancel
          </Button>
        </Form.Item>
      </Form>
    </Modal>
  );
};

export default AddSupplier;
