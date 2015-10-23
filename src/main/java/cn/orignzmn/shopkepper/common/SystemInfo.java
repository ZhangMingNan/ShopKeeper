package cn.orignzmn.shopkepper.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.filechooser.FileSystemView;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class SystemInfo{
  List<CpuInfo> CPU;
  private long memoryTotal;
  private long memoryFree;
  private long memoryUsed;
  private long jvmTotalMemory;
  private long jvmFreeMemory;
  private long jvmUsedMemory;
  private int jvmAvailableProcessors;
  private String jvmVersion;
  private String jvmVendor;
  private String jvmName;
  private String javaVersion;
  private String javaPath;
  private String javaHome;
  private String diskPath;
  private long diskTotal;
  private long diskFree;
  private long diskUsed;
  private long diskAvail;

  public void loadData(Sigar sigar, String strPath)
  {
    if (this.CPU == null) {
      this.CPU = new ArrayList();
    }
    try
    {
      org.hyperic.sigar.CpuInfo[] infos = sigar.getCpuInfoList();
      CpuPerc[] cpuList = null;
      cpuList = sigar.getCpuPercList();
      for (int i = 0; i < infos.length; i++) {
        this.CPU.add(new CpuInfo(i + 1, cpuList[i]));
        ((CpuInfo)this.CPU.get(this.CPU.size() - 1)).setCacheSize(infos[i].getCacheSize());
      }
    }
    catch (SigarException e) {
      e.printStackTrace();
    }
    Runtime r = Runtime.getRuntime();
    Properties props = System.getProperties();

    this.jvmTotalMemory = (r.totalMemory() / 1024L / 1024L);
    this.jvmFreeMemory = (r.freeMemory() / 1024L / 1024L);
    this.jvmUsedMemory = (this.jvmTotalMemory - this.jvmFreeMemory);
    this.jvmAvailableProcessors = r.availableProcessors();

    this.jvmVersion = props.getProperty("java.vm.specification.version");
    this.jvmVendor = props.getProperty("java.vm.vendor");
    this.jvmName = props.getProperty("java.vm.name");
    try
    {
      Mem mem = sigar.getMem();
      this.memoryTotal = (mem.getTotal() / 1024L / 1024L);
      this.memoryUsed = (mem.getUsed() / 1024L / 1024L);
      this.memoryFree = (mem.getFree() / 1024L / 1024L);
    }
    catch (SigarException e) {
      e.printStackTrace();
    }

    this.javaHome = props.getProperty("java.home");
    this.javaPath = props.getProperty("java.class.path");
    this.javaVersion = props.getProperty("java.class.version");

    this.diskPath = strPath;

    FileSystemView fsv = FileSystemView.getFileSystemView();
    File[] fs = File.listRoots();

    for (int i = 0; i < fs.length; i++)
      if (strPath.startsWith(fs[i].getPath())) {
        this.diskTotal = (fs[i].getTotalSpace() / 1024L / 1024L);

        this.diskUsed = ((fs[i].getTotalSpace() - fs[i].getUsableSpace()) / 1024L / 1024L);
        this.diskFree = (fs[i].getFreeSpace() / 1024L / 1024L);
        this.diskAvail = (fs[i].getFreeSpace() / 1024L / 1024L);

        break;
      }
  }

  public List<CpuInfo> getCPU()
  {
    return this.CPU;
  }

  public void setCPU(List<CpuInfo> cPU) {
    this.CPU = cPU;
  }

  public long getMemoryTotal()
  {
    return this.memoryTotal;
  }

  public void setMemoryTotal(long memoryTotal) {
    this.memoryTotal = memoryTotal;
  }

  public long getMemoryFree() {
    return this.memoryFree;
  }

  public void setMemoryFree(long memoryFree) {
    this.memoryFree = memoryFree;
  }

  public long getMemoryUsed() {
    return this.memoryUsed;
  }

  public void setMemoryUsed(long memoryUsed) {
    this.memoryUsed = memoryUsed;
  }

  public String getDiskPath()
  {
    return this.diskPath;
  }

  public void setDiskPath(String diskPath) {
    this.diskPath = diskPath;
  }

  public long getDiskTotal() {
    return this.diskTotal;
  }

  public void setDiskTotal(long diskTotal) {
    this.diskTotal = diskTotal;
  }

  public long getDiskFree() {
    return this.diskFree;
  }

  public void setDiskFree(long diskFree) {
    this.diskFree = diskFree;
  }

  public long getDiskUsed() {
    return this.diskUsed;
  }

  public void setDiskUsed(long diskUsed) {
    this.diskUsed = diskUsed;
  }

  public long getDiskAvail() {
    return this.diskAvail;
  }

  public void setDiskAvail(long diskAvail) {
    this.diskAvail = diskAvail;
  }

  public long getJvmTotalMemory()
  {
    return this.jvmTotalMemory;
  }

  public void setJvmTotalMemory(long jvmTotalMemory) {
    this.jvmTotalMemory = jvmTotalMemory;
  }

  public long getJvmFreeMemory() {
    return this.jvmFreeMemory;
  }

  public void setJvmFreeMemory(long jvmFreeMemory) {
    this.jvmFreeMemory = jvmFreeMemory;
  }

  public long getJvmUsedMemory() {
    return this.jvmUsedMemory;
  }

  public void setJvmUsedMemory(long jvmUsedMemory) {
    this.jvmUsedMemory = jvmUsedMemory;
  }

  public int getJvmAvailableProcessors()
  {
    return this.jvmAvailableProcessors;
  }

  public void setJvmAvailableProcessors(int jvmAvailableProcessors) {
    this.jvmAvailableProcessors = jvmAvailableProcessors;
  }

  public String getJvmVersion() {
    return this.jvmVersion;
  }

  public void setJvmVersion(String jvmVersion) {
    this.jvmVersion = jvmVersion;
  }

  public String getJvmVendor() {
    return this.jvmVendor;
  }

  public void setJvmVendor(String jvmVendor) {
    this.jvmVendor = jvmVendor;
  }

  public String getJvmName() {
    return this.jvmName;
  }

  public void setJvmName(String jvmName) {
    this.jvmName = jvmName;
  }

  public String getJavaVersion()
  {
    return this.javaVersion;
  }

  public void setJavaVersion(String javaVersion) {
    this.javaVersion = javaVersion;
  }

  public String getJavaPath() {
    return this.javaPath;
  }

  public void setJavaPath(String javaPath) {
    this.javaPath = javaPath;
  }

  public String getJavaHome() {
    return this.javaHome;
  }

  public void setJavaHome(String javaHome) {
    this.javaHome = javaHome;
  }
}

