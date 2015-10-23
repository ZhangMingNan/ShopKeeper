package cn.orignzmn.shopkepper.common;

import org.hyperic.sigar.CpuPerc;

public class CpuInfo{
  private int Index;
  private long CacheSize;
  private double User;
  private double Sys;
  private double Wait;
  private double Nice;
  private double Idle;
  private double Combined;

  public CpuInfo(int cpuIndex, CpuPerc cpu)
  {
    this.Index = cpuIndex;

    this.User = cpu.getUser();
    this.Sys = cpu.getSys();
    this.Wait = cpu.getWait();
    this.Nice = cpu.getNice();
    this.Idle = cpu.getIdle();
    this.Combined = cpu.getCombined();
  }

  public int getIndex()
  {
    return this.Index;
  }
  public void setIndex(int index) {
    this.Index = index;
  }
  public long getCacheSize() {
    return this.CacheSize;
  }
  public void setCacheSize(long cacheSize) {
    this.CacheSize = cacheSize;
  }
  public double getUser() {
    return this.User;
  }
  public void setUser(double user) {
    this.User = user;
  }
  public double getSys() {
    return this.Sys;
  }
  public void setSys(double sys) {
    this.Sys = sys;
  }
  public double getWait() {
    return this.Wait;
  }
  public void setWait(double wait) {
    this.Wait = wait;
  }  
  public double getNice() {
    return this.Nice;
  }
  public void setNice(double nice) {
    this.Nice = nice;
  }
  public double getIdle() {
    return this.Idle;
  }
  public void setIdle(double idle) {
    this.Idle = idle;
  }
  public double getCombined() {
    return this.Combined;
  }
  public void setCombined(double combined) {
    this.Combined = combined;
  }
}