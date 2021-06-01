package unity;

import java.io.Serializable;
import java.util.Date;

/*
* 创建实体类  原因ResultSet会被关闭导致数据无法传递
* 实体类类名与数据库名相同
* 类中属性与数据库中一致，并且一般设置为私有
* 属性一般用引用数据类型
* 提供set()get()方法和空参构造器
* */
public class Emp implements Serializable {

	private  Integer empno;
	private  String ename;
	private  String job;
	private  Integer mgr;
	private  Date hiredate;
	private  Double sal;
	private  Double comm;
	private  Integer deptno;

	public Emp(Integer empno, String ename, String job, Integer mgr, Date hiredate, Double sal, Double comm, Integer deptno) {
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.hiredate = hiredate;
		this.sal = sal;
		this.comm = comm;
		this.deptno = deptno;
	}

	public Integer getEmpno() {
		return empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Integer getMgr() {
		return mgr;
	}

	public void setMgr(Integer mgr) {
		this.mgr = mgr;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public Double getSal() {
		return sal;
	}

	public void setSal(Double sal) {
		this.sal = sal;
	}

	public Double getComm() {
		return comm;
	}

	public void setComm(Double comm) {
		this.comm = comm;
	}

	public Integer getDeptno() {
		return deptno;
	}

	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}

	public Emp() {
	}

	@Override
	public String toString() {
		return "emp{" +
				"empno=" + empno +
				", ename='" + ename + '\'' +
				", job='" + job + '\'' +
				", mgr=" + mgr +
				", hiredate=" + hiredate +
				", sal=" + sal +
				", comm=" + comm +
				", deptno=" + deptno +
				'}';
	}
}
