package org.jenkinsci.plugins.interface_example;

import hudson.AbortException;
import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.jenkinsci.plugins.pretestedintegration.AbstractSCMInterface;
import org.jenkinsci.plugins.pretestedintegration.Commit;
import org.jenkinsci.plugins.pretestedintegration.SCMInterfaceDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

/**
 * Example SCM interface implementation for pretested integration plugin
 * @author Ronni Elken Lindsgaard <ronni@diku.dk>
 *
 */
public class ExampleSCM extends AbstractSCMInterface {
	
	/**
	 * Class constructor
	 */
	@DataBoundConstructor
	public ExampleSCM(){
		
	}
	
	/**
	 * The method should return an extension of the AbstractCommit<?> class specifying the 
	 * next commit to be merged and verified calculated from the last commit residing on 
	 * both the integration and staging branch.
	 * @param build
	 * @param launcher
	 * @param listener
	 * @param commit The commit from the last build
	 * @return The next commit to be tested
	 */
	@Override
	public Commit<String> nextCommit(
			AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener, Commit<?> commit)
			throws IOException, IllegalArgumentException {
				return null;
	}
	
	/**
	 * The method is invoked before the build starts, and after the SCM plugin has downloaded 
	 * repository changes. 
	 * When the method terminates a branch with the merge of the integration branch and the 
	 * passed commit should be checked out.
	 * @param build
	 * @param launcher
	 * @param listener
	 * @param commit The commit returned by nextCommit
	 */
	@Override
	public void prepareWorkspace(AbstractBuild<?, ?> build, Launcher launcher,
			BuildListener listener, Commit<?> commit)
			throws AbortException, IOException, IllegalArgumentException {
		//this method is intentionally left blank
	}
	
	/**
	 * Merge the changes into the integration branch
	 * @param build
	 * @param launcher
	 * @param listener
	 */
	@Override
	public void commit(AbstractBuild<?, ?> build, Launcher launcher,
			BuildListener listener) throws IOException, InterruptedException {
		//this method is intentionally left blank
	}
	
	/**
	 * Rollback any changes made to the workspace
	 */
	@Override
	public void rollback(AbstractBuild<?, ?> build, Launcher launcher,
			BuildListener listener) throws IOException, InterruptedException {
		//this method is intentionally left blank
	}
	
	/**
	 * DescriptorImpl for the extension point
	 */
	@Extension
	public static final class DescriptorImpl extends SCMInterfaceDescriptor<ExampleSCM> {
		
		public String getDisplayName(){
			return "Example";
		}
		
		@Override
		public ExampleSCM newInstance(StaplerRequest req, JSONObject formData) throws FormException {
			ExampleSCM i = (ExampleSCM) super.newInstance(req, formData);
			return i;
		}
	}
}
