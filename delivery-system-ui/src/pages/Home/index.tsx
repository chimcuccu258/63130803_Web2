import React, { useEffect, useState } from "react";
import { Supplier, User } from "../../services/api";
import Admin from "../Admin";
import Employee from "../Employee";
import {
  MenuFoldOutlined,
  MenuUnfoldOutlined,
  ShopOutlined,
  UserOutlined,
  CarryOutOutlined,
  LogoutOutlined,
} from "@ant-design/icons";
import { Avatar, Button, Flex, Layout, Menu, Space, Spin, theme } from "antd";
import "./styles.css";
import Order from "../Order";
import UserDetails from "./components/UserDetails";
import SupplierScreen from "../Supplier/index";

const { Header, Sider, Content } = Layout;

interface HomeProps {
  isLoggedIn: boolean;
  onLogout: () => void;
  user: User | null;
}

const Home: React.FC<HomeProps> = ({ isLoggedIn, onLogout, user }) => {
  const [collapsed, setCollapsed] = useState(false);
  const [selectedMenuItem, setSelectedMenuItem] = useState("1");
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [isLoading, setIsLoading] = useState(true);
  const [suppliers, setSuppliers] = useState<Supplier[] | null>(null);
  // console.log(localStorage.getItem("token"));
  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();

  const handleModalClose = () => {
    setIsModalVisible(false);
  };

  useEffect(() => {
    if (user) {
      console.log("User data loaded:", user);
      setIsLoading(false);
    } else {
      console.log("User data not loaded yet");
    }
  }, [user]);

  const renderContent = () => {
    switch (selectedMenuItem) {
      case "1":
        return <Employee user={user} />;
      case "2":
        return <SupplierScreen />;
      case "3":
        return <Order />;
      default:
        return <Employee user={user} />;
    }
  };

  return (
    <div>
      {isLoggedIn && (
        <Layout style={{ minHeight: "100vh" }}>
          <Sider trigger={null} collapsible collapsed={collapsed}>
            <div
              style={{
                padding: 10,
              }}
              onClick={() => setIsModalVisible(true)}
            >
              <Avatar size="large" icon={<UserOutlined />} />
              <text
                style={{
                  color: "white",
                }}
              >
                {" "}
                {user?.fullName}
              </text>
            </div>
            <Menu
              theme="dark"
              mode="inline"
              defaultSelectedKeys={["1"]}
              onSelect={({ key }) => setSelectedMenuItem(key)}
              items={[
                {
                  key: "1",
                  icon: <UserOutlined />,
                  label: "Employee",
                },
                {
                  key: "2",
                  icon: <ShopOutlined />,
                  label: "Supplier",
                },
                {
                  key: "3",
                  icon: <CarryOutOutlined />,
                  label: "Order",
                },
              ]}
            />
          </Sider>
          <Layout>
            <Header
              style={{
                padding: 0,
                background: colorBgContainer,
                justifyContent: "space-between",
              }}
            >
              <Button
                type="text"
                icon={collapsed ? <MenuUnfoldOutlined /> : <MenuFoldOutlined />}
                onClick={() => setCollapsed(!collapsed)}
                style={{
                  fontSize: "16px",
                  width: 64,
                  height: 64,
                }}
              />
              <Button
                type="text"
                icon={<LogoutOutlined />}
                onClick={onLogout}
                style={{
                  fontSize: "16px",
                }}
              />
            </Header>
            <Content
              style={{
                margin: "24px 16px",
                padding: 24,
                minHeight: 280,
                background: colorBgContainer,
                borderRadius: borderRadiusLG,
              }}
            >
              {/* {renderContent()} */}
              {isLoading ? <Spin /> : renderContent()}
            </Content>
          </Layout>
          <UserDetails
            visible={isModalVisible}
            onClose={handleModalClose}
            user={user}
          />
        </Layout>
      )}
    </div>
  );
};

export default Home;
