import React, { useState } from "react";
import { Supplier } from "../../../services/api";
import { Button, Modal, Form, Input, message } from "antd";

interface Props {
  supplier: Supplier;
}

const EditSupplier: React.FC<Props> = ({ supplier }) => {
  const [visible, setVisible] = useState(false);
  const [form] = Form.useForm();

  const showModal = () => {
    setVisible(true);
    form.setFieldsValue(supplier);
  };

  const handleCancel = () => {
    setVisible(false);
  };

  const saveSupplier = async (values: any) => {
    try {
      const storedToken = localStorage.getItem("token");
      if (!storedToken) {
        throw new Error("Token not found in localStorage");
      }

      const response = await fetch(
        `http://localhost:8080/admin/v1/manage-supplier/update-supplier`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${storedToken}`,
          },
          body: JSON.stringify(values),
        }
      );

      if (!response.ok) {
        throw new Error("Failed to update supplier");
      }

      message.success("Supplier updated successfully");
    } catch (error) {
      console.error("Error updating supplier:", error);
      message.error("Failed to update supplier");
    } finally {
      setVisible(false);
    }
  };

  return (
    <>
      <a onClick={showModal}>Edit</a>
      <Modal
        title="Edit Supplier"
        centered
        visible={visible}
        onCancel={handleCancel}
        footer={[
          <Button key="close" onClick={handleCancel}>
            Close
          </Button>,
          <Button key="save" type="primary" onClick={() => form.submit()}>
            Save
          </Button>,
        ]}
      >
        <Form
          form={form}
          layout="vertical"
          onFinish={saveSupplier}
          initialValues={supplier}
        >
          <Form.Item name="supplierID" label="Supplier ID">
            <Input disabled />
          </Form.Item>
          <Form.Item
            name="supplierName"
            label="Supplier Name"
            rules={[
              { required: true, message: "Please input the supplier name!" },
            ]}
          >
            <Input />
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
        </Form>
      </Modal>
    </>
  );
};

export default EditSupplier;
