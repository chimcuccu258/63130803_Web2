import { User } from "../../services/api";
import EmployeeTable from "./components/EmployeeTable";
import ban from "../../assets/images/Ban.jpg";
import { Tag } from "antd";

interface EmployeeProps {
  user: User | null;
}

const Employee: React.FC<EmployeeProps> = ({ user }) => {
  return (
    <div>
      {
        user?.email.endsWith("@dls.co.com")? (
          <EmployeeTable user={user} />
        ) : (
          <div
            style={{
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
              flexDirection: "column",
            }}
          >
            <h1
              style={{
                color: "red",
                fontSize: "20px",
                fontWeight: "bold",
                textAlign: "center",
                marginTop: "20px",
                marginBottom: "20px",
              }}
            >
              Can not access
            </h1>
          </div>
        )
      }
    </div>
  );
};

export default Employee;
