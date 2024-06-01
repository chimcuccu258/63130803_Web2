import React, { useState } from "react";
import { Employee } from "../../../services/api";
import { Button, Modal, Form, Input, message } from "antd";

interface Props {
  employee: Employee;
}

const EditEmployee: React.FC<Props> = ({ employee }) => {
  const [visible, setVisible] = useState(false);
  const [form] = Form.useForm();

  const showModal = () => {
    setVisible(true);
    form.setFieldsValue(employee);
  };

  const handleCancel = () => {
    setVisible(false);
  };

  const saveEmployee = async (values: any) => {
    try {
      const storedToken = localStorage.getItem("token");
      if (!storedToken) {
        throw new Error("Token not found in localStorage");
      }

      const response = await fetch(
        `http://localhost:8080/admin/v1/manage-employee/update-employee`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${storedToken}`,
          },
          body: JSON.stringify({ ...values, employeeID: employee.employeeID }),
        }
      );

      if (!response.ok) {
        throw new Error("Failed to update employee");
      }

      message.success("Employee updated successfully");
    } catch (error) {
      console.error("Error updating employee:", error);
      message.error("Failed to update employee");
    } finally {
      setVisible(false);
    }
  };

  return (
    <>
      <a onClick={showModal}>Edit</a>
      <Modal
        title="Information Employee"
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
          onFinish={saveEmployee}
          initialValues={employee}
        >
          <Form.Item name="employeeID" label="Employee ID">
            <Input disabled />
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

export default EditEmployee;
