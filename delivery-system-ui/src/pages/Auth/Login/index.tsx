import React, { useState } from "react";
import FormLogin from "./components/FormLogin";
import { Flex, Typography } from "antd";
import "./styles.css";
import {
  getCurrentUser,
  login,
  LoginRequest,
  LoginResponse,
  User,
} from "../../../services/api";

const { Text, Title } = Typography;

interface LoginProps {
  onSubmitLogin: (user: User) => void;
}

const Login: React.FC<LoginProps> = ({ onSubmitLogin }) => {
  const [error, setError] = useState<string>("");

  const onLogin = async (loginRequest: LoginRequest) => {
    try {
      const { token, role }: LoginResponse = await login(loginRequest);
      const user: User = await getCurrentUser(loginRequest.email, token, role);
      // console.log(token)
      localStorage.setItem("token", token);
      onSubmitLogin(user);
    } catch (error) {
      if (error instanceof Error) {
        setError(error.message);
      } else {
        setError("An unknown error occurred");
      }
    }
  };

  return (
    <div className="login-container">
      <Flex vertical align="center">
        <Title>Login</Title>
        {error && <Text type="danger">{error}</Text>}
        <FormLogin onSubmit={onLogin} />
      </Flex>
    </div>
  );
};

export default Login;
