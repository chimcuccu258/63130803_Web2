import React, { useState } from "react";
import { Modal, Form, Input, Button, Radio } from "antd";

interface AddEmployeeProps {
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
      addonAfter="@dls.em.com"
      placeholder="Enter email prefix"
    />
  );
};

const AddEmployee: React.FC<AddEmployeeProps> = ({ open, onCancel, onAdd }) => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);

  const onSubmit = async (values: any) => {
    setLoading(true);
    try {
      const response = await fetch(
        "http://localhost:8080/auth/employee-signup",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            ...values,
            email: values.email + "@dls.em.com",
          }),
        }
      );

      if (!response.ok) {
        throw new Error("Network response was not ok");
      }

      form.resetFields();
      onAdd();
    } catch (error) {
      console.error("Error adding employee:", error);
    } finally {
      setLoading(false);
      onCancel();
    }
  };

  return (
    <Modal
      title="Add Employee"
      centered
      open={open}
      onCancel={onCancel}
      footer={null}
    >
      <Form form={form} layout="vertical" onFinish={onSubmit}>
        <Form.Item
          name="fullName"
          label="Full Name"
          rules={[{ required: true, message: "Please input the full name!" }]}
        >
          <Input />
        </Form.Item>
        <Form.Item
          name="dateOfBirth"
          label="Date of Birth"
          rules={[
            { required: true, message: "Please input the date of birth!" },
          ]}
        >
          <Input type="date" />
        </Form.Item>
        <Form.Item
          name="gender"
          label="Gender"
          rules={[{ required: true, message: "Please select the gender!" }]}
        >
          <Radio.Group>
            <Radio value={true}>Male</Radio>
            <Radio value={false}>Female</Radio>
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
          {/* <Input type="email" /> */}
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
            Add Employee
          </Button>
          <Button type="text" onClick={onCancel}>
            Cancel
          </Button>
        </Form.Item>
      </Form>
    </Modal>
  );
};

export default AddEmployee;
