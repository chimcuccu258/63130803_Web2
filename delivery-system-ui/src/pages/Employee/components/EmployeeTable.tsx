import React, { useEffect, useState } from "react";
import { Button, Flex, Modal, Space, Table, Tag } from "antd";
import type { TableProps } from "antd";
import { Employee, fetchEmployees, User } from "../../../services/api";
import { formatDate } from "../../../utils/formatDate";
import AddEmployee from "./AddEmployee";
import InformationEmployee from "./InformationEmployee";
import EditEmployee from "./EditEmployee";

const EmployeeTable: React.FC<{ user: User | null }> = ({ user }) => {
  const [data, setData] = useState<Employee[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const [addEmployeeModalVisible, setAddEmployeeModalVisible] =
    useState<boolean>(false);

  const fetchData = async () => {
    try {
      const storedToken = localStorage.getItem("token");
      if (!storedToken) {
        throw new Error("Token not found in localStorage");
      }
      const employeesData = await fetchEmployees(storedToken);
      setData(employeesData);
      console.log(employeesData);
    } catch (error) {
      setError(
        error instanceof Error ? error.message : "Unknown error occurred"
      );
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const columns: TableProps<Employee>["columns"] = [
    {
      title: "ID",
      dataIndex: "employeeID",
      key: "employeeID",
    },
    {
      title: "Full Name",
      dataIndex: "fullName",
      key: "fullName",
    },
    {
      title: "Date of Birth",
      dataIndex: "dateOfBirth",
      key: "dateOfBirth",
      render: (dateOfBirth: string) => formatDate(dateOfBirth),
    },
    {
      title: "Gender",
      dataIndex: "gender",
      key: "gender",
      render: (gender: boolean) => (gender ? "Male" : "Female"),
    },
    {
      title: "Address",
      dataIndex: "address",
      key: "address",
    },
    {
      title: "Role",
      key: "role",
      render: (_, employee) => {
        if (employee.email.endsWith("@dls.co.com")) {
          return <Tag color="blue">Admin</Tag>;
        } else if (employee.email.endsWith("@dls.em.com")) {
          return <Tag color="green">Employee</Tag>;
        }
        return null;
      },
    },
    {
      title: "Action",
      key: "action",
      render: (_: any, employee: Employee) => (
        <Space size="middle">
          <EditEmployee employee={employee} />
          <InformationEmployee employee={employee} />
        </Space>
      ),
    },
  ];

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div>
      <Flex justify="space-between" align="center">
        <h3>Employees</h3>
        <Button type="primary" onClick={() => setAddEmployeeModalVisible(true)}>
          + Add new employee
        </Button>
      </Flex>
      <Table columns={columns} dataSource={data} />
      <AddEmployee
        open={addEmployeeModalVisible}
        onCancel={() => setAddEmployeeModalVisible(false)}
        onAdd={() => {
          setAddEmployeeModalVisible(false);
          fetchData();
        }}
      />
    </div>
  );
};

export default EmployeeTable;
