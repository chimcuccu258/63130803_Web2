import React from "react";
import { Modal, Table, Tag } from "antd";
import { User } from "../../../services/api";
import { formatDate } from "../../../utils/formatDate";

interface UserDetailsProps {
  visible: boolean;
  onClose: () => void;
  user: User | null;
}

const UserDetails: React.FC<UserDetailsProps> = ({
  visible,
  onClose,
  user,
}) => {
  const getRole = (email: string | undefined) => {
    if (!email) return "Unknown";
    const emailDomain = email.split("@")[1];
    switch (emailDomain) {
      case "dls.co.com":
        return "Admin";
      case "dls.em.com":
        return "Employee";
      case "dls.su.com":
        return "Supplier";
      default:
        return "Unknown";
    }
  };

  const role = getRole(user?.email);

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
      value: user?.employeeID,
    },
    {
      key: "Full Name",
      field: "Full Name",
      value: user?.fullName,
    },
    {
      key: "Date of Birth",
      field: "Date of Birth",
      value: formatDate(user?.dateOfBirth ?? ""),
    },
    {
      key: "Gender",
      field: "Gender",
      value: user?.gender ? "Male" : "Female",
    },
    {
      key: "Address",
      field: "Address",
      value: user?.address,
    },
    {
      key: "Phone Number",
      field: "Phone Number",
      value: user?.phoneNumber,
    },
    {
      key: "Role",
      field: "Role",
      value: user?.email.endsWith("@dls.co.com") ? (
        <Tag color="blue">Admin</Tag>
      ) : user?.email.endsWith("@dls.em.com") ? (
        <Tag color="green">Employee</Tag>
      ) : null,
    },
  ];

  return (
    <Modal
      title="User Details"
      centered
      open={visible}
      onCancel={onClose}
      footer={null}
    >
      <Table
        columns={columns}
        dataSource={data}
        pagination={false}
        showHeader={false}
      />
    </Modal>
  );
};

export default UserDetails;
