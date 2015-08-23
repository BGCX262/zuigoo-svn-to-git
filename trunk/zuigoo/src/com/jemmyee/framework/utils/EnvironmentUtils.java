package com.jemmyee.framework.utils;


/**
 * @Description: 系统环境参数
 * @author:jemmyee@gmail.com
 * @date:2010-10-20
 * @version:v1.0
 */
public class EnvironmentUtils {
	
       /*Java 运行时环境版本*/
	   public final static String java_version=System.getProperty("java.version");
	   /*Java 运行时环境供应商*/
	   public final static String java_vendor=System.getProperty("java.vendor");
	   /*Java 供应商的 URL*/
	   public final static String java_vendor_url=System.getProperty("java.vendor.url");
	   /*Java 安装目录*/
	   public final static String java_home=System.getProperty("java.home");
	   /*Java 虚拟机规范版本*/
	   public final static String java_vm_specification_version=System.getProperty("java.vm.specification.version");
	   /*Java 虚拟机规范供应商*/
	   public final static String java_vm_specification_vendor=System.getProperty("java.vm.specification.vendor");
	   /*Java 虚拟机规范名称*/
	   public final static String java_vm_specification_name=System.getProperty("java.vm.specification.name");
	   /*Java 虚拟机实现版本*/
	   public final static String java_vm_version=System.getProperty("java.vm.version");
	   /*Java 虚拟机实现供应商*/
	   public final static String java_vm_vendor=System.getProperty("java.vm.vendor");
	   /*Java 虚拟机实现名称*/
	   public final static String java_vm_name=System.getProperty("java.vm.name");
	   /*Java 运行时环境规范版本*/
	   public final static String java_specification_version=System.getProperty("java.specification.version");
	   /*Java 运行时环境规范供应商*/
	   public final static String java_specification_vendor=System.getProperty("java.specification.vendor");
	   /*Java 运行时环境规范名称*/
	   public final static String java_specification_name=System.getProperty("java.specification.name");
	   /*Java 类格式版本号*/
	   public final static String java_class_version=System.getProperty("java.class.version");
	   /*Java 类路径*/
	   public final static String java_class_path=System.getProperty("java.class.path");
	   /*加载库时搜索的路径列表*/
	   public final static String java_library_path=System.getProperty("java.library.path");
	   /*默认的临时文件路径*/
	   public final static String java_io_tmpdir=System.getProperty("java.io.tmpdir");
	   /*要使用的 JIT 编译器的名称*/
	   public final static String java_compiler=System.getProperty("java.compiler");
	   /*一个或多个扩展目录的路径*/
	   public final static String java_ext_dirs=System.getProperty("java.ext.dirs");
	   /*操作系统的名称*/
	   public final static String os_name=System.getProperty("os.name");
	   /*操作系统的架构*/
	   public final static String os_arch=System.getProperty("os.arch");
	   /*操作系统的版本*/
	   public final static String os_version=System.getProperty("os.version");
	   /*用户的账户名称*/
	   public final static String user_name=System.getProperty("user.name");
	   /*用户的主目录*/
	   public final static String user_home=System.getProperty("user.home");
	   /*用户的当前工作目录*/
	   public final static String user_dir=System.getProperty("user.dir");
	   
	   /*Java 虚拟机试图使用的最大内存量*/
	   public final static Long max_memory=Runtime.getRuntime().maxMemory()/1000000;
	   /*Java 虚拟机中的内存总量*/
	   public final static Long total_memory=Runtime.getRuntime().totalMemory()/1000000;
	   /*Java 虚拟机中的空闲内存量*/
	   public final static Long free_memory=Runtime.getRuntime().freeMemory()/1000000;
	   
	
	   
	   
	   
	   
	   
	   
	/**
	 * function:
	 * 
	 * @param args
	 * @date 2009-2-5
	 * @time 下午02:50:05
	 */
	public static void main(String[] args) {
		System.out.println("java.home:" + System.getProperty("java.home"));
		System.out
				.println("java.version:" + System.getProperty("java.version"));
		System.out.println("java.class.path:"
				+ System.getProperty("java.class.path"));
		System.out.println("java.library.path:"
				+ System.getProperty("java.library.path"));
		System.out.println("java.io.tmpdir:"
				+ System.getProperty("java.io.tmpdir"));
		System.out.println("java.compiler:"
				+ System.getProperty("java.compiler"));
		System.out.println("os.name:" + System.getProperty("os.name"));
		System.out.println("os.arch:" + System.getProperty("os.arch"));
		System.out.println("os.version:" + System.getProperty("os.version"));
		System.out.println("path.separator:"
				+ System.getProperty("path.separator"));
		System.out.println("line.separator:"
				+ System.getProperty("line.separator"));
		System.out.println("file.separator:"
				+ System.getProperty("file.separator").charAt(0));
		System.out.println("user.name:" + System.getProperty("user.name"));
		System.out.println("user.home:" + System.getProperty("user.home"));
		System.out.println("user.dir:" + System.getProperty("user.dir"));
	}
}
