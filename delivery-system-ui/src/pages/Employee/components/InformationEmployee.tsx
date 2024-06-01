import React from "react";
import { Modal, Button, Table, Tag } from "antd";
import { formatDate } from "../../../utils/formatDate";
import { formatDateTime } from "../../../utils/formatDateTime";
import { Employee } from "../../../services/api";

interface Props {
  employee: Employee;
}

const InformationEmployee: React.FC<Props> = ({ employee }) => {
  const [visible, setVisible] = React.useState(false);

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

  const data = [
    {
      key: "ID",
      field: "ID",
      value: employee.employeeID,
    },
    {
      key: "Full Name",
      field: "Full Name",
      value: employee.fullName,
    },
    {
      key: "Date of Birth",
      field: "Date of Birth",
      value: formatDate(employee.dateOfBirth),
    },
    {
      key: "Gender",
      field: "Gender",
      value: employee.gender ? "Male" : "Female",
    },
    {
      key: "Address",
      field: "Address",
      value: employee.address,
    },
    {
      key: "Phone Number",
      field: "Phone Number",
      value: employee.phoneNumber,
    },
    {
      key: "Role",
      field: "Role",
      value: employee.email.endsWith("@dls.co.com") ? (
        <Tag color="blue">Admin</Tag>
      ) : employee.email.endsWith("@dls.em.com") ? (
        <Tag color="green">Employee</Tag>
      ) : null,
    },
    {
      key: "Created At",
      field: "Created At",
      value: formatDateTime(employee.created_at),
    },
    {
      key: "Updated At",
      field: "Updated At",
      value: formatDateTime(employee.updated_at),
    },
  ];

  return (
    <>
      <a onClick={showModal}>Information</a>
      <Modal
        title="Information Employee"
        centered
        open={visible}
        onCancel={handleCancel}
        footer={[
          <Button key="close" onClick={handleCancel}>
            Close
          </Button>,
        ]}
      >
        <Table
          columns={columns}
          dataSource={data}
          pagination={false}
          showHeader={false}
        />
      </Modal>
    </>
  );
};

export default InformationEmployee;
