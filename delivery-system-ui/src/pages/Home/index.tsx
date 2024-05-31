import React, { useState } from "react";
import { User } from "../../services/api";
import Admin from "../Admin";
import Employee from "../Employee";
import {
  MenuFoldOutlined,
  MenuUnfoldOutlined,
  UploadOutlined,
  UserOutlined,
  VideoCameraOutlined,
} from "@ant-design/icons";
import { Button, Layout, Menu, theme } from "antd";
import "./styles.css";

const { Header, Sider, Content } = Layout;

interface HomeProps {
  isLoggedIn: boolean;
  onLogout: () => void;
  user: User | null;
}

const Home: React.FC<HomeProps> = ({ isLoggedIn, onLogout, user }) => {
  const [collapsed, setCollapsed] = useState(false);
  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();

  const renderUserType = () => {
    if (!user) return null;

    const emailDomain = user.email.split("@")[1];

    if (emailDomain === "dls.co.com") {
      return <Admin />;
    } else if (emailDomain === "dls.em.com") {
      return <Employee />;
    } else {
      return null;
    }
  };

  return (
    <div>
      {/* <h2>Home</h2>
      {isLoggedIn ? (
        <div>
          <p>Welcome, {user?.fullName}!</p>
          <p>Email: {user?.email}</p>
          <button onClick={onLogout}>Logout</button>
          {renderUserType()}
        </div>
      ) : (
        <p>Please log in to access more features.</p>
      )} */}
      <Layout style={{ minHeight: "100vh" }}>
        <Sider trigger={null} collapsible collapsed={collapsed}>
          <div className="demo-logo-vertical" />
          <Menu
            theme="dark"
            mode="inline"
            defaultSelectedKeys={["1"]}
            items={[
              {
                key: "1",
                icon: <UserOutlined />,
                label: "nav 1",
              },
              {
                key: "2",
                icon: <VideoCameraOutlined />,
                label: "nav 2",
              },
              {
                key: "3",
                icon: <UploadOutlined />,
                label: "nav 3",
              },
            ]}
          />
        </Sider>
        <Layout>
          <Header style={{ padding: 0, background: colorBgContainer }}>
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
            Content
          </Content>
        </Layout>
      </Layout>
    </div>
  );
};

export default Home;
