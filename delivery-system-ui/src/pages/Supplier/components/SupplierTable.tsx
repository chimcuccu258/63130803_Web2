import React, { useEffect, useState } from "react";
import { Button, Flex, Space, Table, Tag, message } from "antd";
import { fetchSuppliers, Supplier } from "../../../services/api";
import { formatDateTime } from "../../../utils/formatDateTime";
import AddSupplier from "./AddSupplier";
import EditSupplier from "./EditSupplier";

const SupplierTable: React.FC = () => {
  const [data, setData] = useState<Supplier[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const [addSupplierModalVisible, setAddSupplierModalVisible] =
    useState<boolean>(false);

  const fetchData = async () => {
    try {
      const storedToken = localStorage.getItem("token");
      if (!storedToken) {
        throw new Error("Token not found in localStorage");
      }
      const suppliersData = await fetchSuppliers(storedToken);
      setData(suppliersData);
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

  const columns = [
    {
      title: "ID",
      dataIndex: "supplierID",
      key: "supplierID",
    },
    {
      title: "Supplier Name",
      dataIndex: "supplierName",
      key: "supplierName",
    },
    {
      title: "Status",
      dataIndex: "supplierStatus",
      key: "supplierStatus",
      render: (status: string) => (
        <Tag color={status === "Opening" ? "green" : "red"}>{status}</Tag>
      ),
    },
    {
      title: "Email",
      dataIndex: "email",
      key: "email",
    },
    {
      title: "Address",
      dataIndex: "address",
      key: "address",
    },
    {
      title: "Phone Number",
      dataIndex: "phoneNumber",
      key: "phoneNumber",
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
    {
      title: "Action",
      key: "action",
      render: (_: any, supplier: Supplier) => (
        <Space size="middle">
          <EditSupplier supplier={supplier} />
        </Space>
      ),
    },
  ];

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div>
      <Flex justify="space-between" align="center">
        <h3>Suppliers</h3>
        <Button type="primary" onClick={() => setAddSupplierModalVisible(true)}>
          + Add new supplier
        </Button>
      </Flex>
      <Table columns={columns} dataSource={data} />
      <AddSupplier
        open={addSupplierModalVisible}
        onCancel={() => setAddSupplierModalVisible(false)}
        onAdd={() => {
          setAddSupplierModalVisible(false);
          fetchData();
        }}
      />
    </div>
  );
};

export default SupplierTable;
