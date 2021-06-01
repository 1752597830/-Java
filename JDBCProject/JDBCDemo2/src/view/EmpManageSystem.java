package view;

import test.dao.impl.DeptDaoImpl;
import test.dao.impl.EmpDaoImpl;
import test.pojo.Dept;
import test.pojo.Emp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @Auther: qinfeng
 * @Date: 2021/5/23 - 05 - 23 - 16:21
 * @Description: view
 */
public class EmpManageSystem {
    private static Scanner sc=new Scanner(System.in);
    private static SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    private static DeptDaoImpl deptDao=new DeptDaoImpl();
    private static EmpDaoImpl empDao=new EmpDaoImpl();
    public static void main(String[] args) {
        o:while(true){
            showMenu();
            System.out.println("请录入选项");
            int option  =sc.nextInt();
            switch (option){
                case 1:
                    case1();
                    break;
                case 2:
                    case2();
                    break;
                case 3:
                    case3();
                    break;
                case 4:
                    case4();
                    break;
                case 5:
                    case5();
                    break;
                case 6:
                    case6();
                    break;
                case 7:
                    sc.close();
                    //System.exit(0);
                    break o;
                default:
                    System.out.println("请正确输入选项");
            }
        }
    }
    private static void case1() {
        List<Emp> list=empDao.EmpFindAll();
        for (Emp emp:list) {
            System.out.println(emp);
        }
    }
    private static void case2(){
        List<Dept> list=deptDao.DeptFindAll();
        list.forEach(System.out::println);
    }
    private static void case3(){
        System.out.println("请输入要删除的员工编号");
        int deptno=sc.nextInt();
        empDao.EmpDelByempno(deptno);
    }
    private static void case4(){
        System.out.println("请输入要修改的员工编号：");
        int empno=sc.nextInt();
        Emp emp=null;
        System.out.println("请输入姓名：");
        String ename=sc.next();
        System.out.println("请输入JOB：");
        String job=sc.next();
        System.out.println("请输入上级编号：");
        Integer mgr=sc.nextInt();
        System.out.println("请输入入职日期：参照格式：yyyy-MM-dd");
        Date hiredate = null;
        try {
            hiredate = dateFormat.parse(sc.next());
        }catch(ParseException e){
            e.printStackTrace();
        }
        System.out.println("请输入薪资：");
        Double sal=sc.nextDouble();
        System.out.println("请输入补助：");
        Double comm=sc.nextDouble();
        System.out.println("请输入部门编号：");
        Integer deptno=sc.nextInt();
        emp=new Emp(empno,ename,job,mgr,hiredate,sal,comm,deptno);
        empDao.EmpUpdateByempno(deptno,emp);
    }
    private static void case5(){
        System.out.println("请输入增加的员工信息：");
        Emp emp=null;
        System.out.println("请输入姓名：");
        String ename=sc.next();
        System.out.println("请输入JOB：");
        String job=sc.next();
        System.out.println("请输入上级编号：");
        Integer mgr=sc.nextInt();
        System.out.println("请输入入职日期：参照格式：yyyy-MM-dd");
        Date hiredate = null;
        try {
            hiredate = dateFormat.parse(sc.next());
        }catch(ParseException e){
            e.printStackTrace();
        }
        System.out.println("请输入薪资：");
        Double sal=sc.nextDouble();
        System.out.println("请输入补助：");
        Double comm=sc.nextDouble();
        System.out.println("请输入部门编号：");
        Integer deptno=sc.nextInt();
        emp=new Emp(null,ename,job,mgr,hiredate,sal,comm,deptno);
        empDao.EmpAdd(emp);
    }
    private static void case6(){
        System.out.println("请输入增加的部门信息：");
        Dept dept=null;
        System.out.println("请输入部门名：");
        String dname=sc.next();
        System.out.println("请输入职位：");
        String loc=sc.next();
        dept=new Dept(null,dname,loc);
        deptDao.DeptAdd(dept);
    }
    private static void case7(){

    }
    public static void showMenu(){
        System.out.println("************************************");
        System.out.println("* 1 查看所有员工信息");
        System.out.println("* 2 查看所有部门信息");
        System.out.println("* 3 根据工号删除员工信息");
        System.out.println("* 4 根据工号修改员工信息");
        System.out.println("* 5 增加员工信息");
        System.out.println("* 6 增加部门信息");
        System.out.println("* 7 退出");
        System.out.println("************************************");
    }
}
