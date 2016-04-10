/**
 * 
 */
package com.patel.pradeep.concurrency.sync;

/**
 * @author prade
 * http://www.devmanuals.com/tutorials/java/corejava/java-synchronization.html
 */
public class MainClaz_01_VIMP {
	public static void main(String[] args) {
		Resource resource = new Resource();
		ResourceUser resourceUser = new ResourceUser(resource, "resourceUser");
		ResourceUser resourceUser1 = new ResourceUser(resource, "resourceUser1");
		ResourceUser resourceUser2 = new ResourceUser(resource, "resourceUser2");
		try {
			new Thread(resourceUser).start();
			new Thread(resourceUser1).start();
			new Thread(resourceUser2).start();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	static class ResourceUser implements Runnable {
		String message;
		Resource resource;
		public ResourceUser(Resource resource, String string) {
			this.resource = resource;
			this.message = string;
		}
		public void run() {
			synchronized (resource) {
				resource.callMethod(message);
			}
			//synchronized (resource) {
				resource.callMethod(message + " PPP");
			//}
		}
	}
	static class Resource {
		void callMethod(String string) {
			System.out.println(string);
			try {
				Thread.sleep(2 * 1000); //2 Second
			} catch (Exception e) {
				System.out.println("Interrupted During Execution");
			}
			System.out.println(string + " Done.");
		}
	}
}
