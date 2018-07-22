package com.uncharted;

import java.sql.ResultSet;

public class EmployeeDAOImpl implements EmployeeDAO
{
    //private static final String INSERT_EMPLOYEE_QUERY="insert into employee values (?)";

    public boolean insertEmployee(EmployeeBO bo) throws Exception
    {
        try {
            String strQuery = "insert into employee value (null,'" + bo.getName() + "')";
            if (DBConnectionSetup.getInsertUpdateDeleteQueryResult(strQuery))
                return true;
            return false;
        }
        catch (Exception ex)
        {
            throw ex;
        }
        finally
        {
            DBConnectionSetup.closeConnection();
        }

    }

    public EmployeeBO getEmployee(int id) throws Exception
    {
        try {
            String strQuery = "select idemployee, employeename from employee where idemployee=" + id + ";";
            ResultSet rs = DBConnectionSetup.getSelectQueryResult(strQuery);
            if (!rs.next()) {                            //if rs.next() returns false
                System.out.println("No records found");
                return null;
            } else {
                EmployeeBO emp;
                boolean flag = false;
                do {
                    if (!flag) {
                        emp = new EmployeeBO();
                        emp.setId(rs.getInt("idemployee"));
                        emp.setName(rs.getString("employeename"));
                        flag = true;
                    } else {
                        System.out.println("More than 1 row with same id");
                        return null;
                    }
                } while (rs.next());
                return emp;
            }
        }
        catch (Exception ex)
        {
            throw ex;
        }
        finally
        {
            DBConnectionSetup.closeConnection();
        }
    }
}
