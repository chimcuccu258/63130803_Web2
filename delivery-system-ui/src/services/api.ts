import { EnumType } from "typescript";

const baseURL = "http://localhost:8080";
const auth = "/auth";
const adminEmployee = "/admin/v1/manage-employee";
const adminSupplier = "/admin/v1/manage-supplier";
const adminCustomer = "/admin/v1/manage-customer";

const employee = "/api/v1/employee";
const supplier = "/api/v1/supplier";

export interface LoginRequest {
  email: string;
  password: string;
}

export interface LoginResponse {
  token: string;
  role: string;
}

export interface GetEmployeesResponse {
  employees: User[];
}

export interface User {
  employeeID: number;
  fullName: string;
  avatar: string | null;
  dateOfBirth: string;
  gender: boolean;
  address: string;
  phoneNumber: string;
  email: string;
  password: string;
  created_at: string;
  updated_at: string;
}

export interface Supplier {
  supplierID: number;
  supplierName: string;
  supplierStatus: "OPEN" | "CLOSE";
  email: string;
  address: string;
  phoneNumber: string;
  password: string;
  created_at: string;
  updated_at: string;
}

export interface Employee {
  employeeID: number;
  fullName: string;
  dateOfBirth: string;
  gender: boolean;
  address: string;
  phoneNumber: string;
  email: string;
  created_at: string;
  updated_at: string;
}

export const login = async (
  loginRequest: LoginRequest
): Promise<LoginResponse> => {
  const { email, password } = loginRequest;
  const response = await fetch(`${baseURL}${auth}/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ email, password }),
  });

  const data = await response.json();

  if (!response.ok) {
    throw new Error(data.message || "Login failed");
  }

  const { token, roles } = data.data;
  const role = roles[0].split("_")[1];

  return { token, role };
};

export const getCurrentUser = async (
  email: string,
  token: string,
  role: string
): Promise<User> => {
  let endpoint;
  switch (role.toLowerCase()) {
    case "admin":
      endpoint = adminEmployee;
      break;
    case "employee":
      endpoint = employee;
      break;
    case "supplier":
      endpoint = supplier;
      break;
    default:
      throw new Error("Invalid user role");
  }

  const response = await fetch(
    `${baseURL}${endpoint}/find-by-email?email=${email}`,
    {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
    }
  );

  const data = await response.json();
  localStorage.setItem("user", JSON.stringify(data.data));

  if (!response.ok) {
    throw new Error(data.message || "Failed to fetch user");
  }

  return data.data;
};

export const fetchEmployees = async (token: any) => {
  try {
    const response = await fetch(`${baseURL}${adminEmployee}/find-all`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
      },
    });
    if (!response.ok) {
      throw new Error("Failed to fetch employees");
    }
    const { data } = await response.json();
    return data;
  } catch (error) {
    throw new Error(
      error instanceof Error ? error.message : "Unknown error occurred"
    );
  }
};

export const fetchSuppliers = async (token: any) => {
  try {
    const response = await fetch(`${baseURL}${adminSupplier}/find-all`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
      },
    });
    if (!response.ok) {
      throw new Error("Failed to fetch suppliers");
    }
    const { data } = await response.json();
    return data;
  } catch (error) {
    throw new Error(
      error instanceof Error? error.message : "Unknown error occurred"
    );
  }
}

