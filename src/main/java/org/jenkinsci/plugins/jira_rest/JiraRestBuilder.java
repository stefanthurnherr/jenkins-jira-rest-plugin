package org.jenkinsci.plugins.jira_rest;
import hudson.Launcher;
import hudson.Extension;
import hudson.util.FormValidation;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.AbstractProject;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.QueryParameter;

import javax.servlet.ServletException;
import java.io.IOException;


/**
 * Builder to connect to and interact with a JIRA instance using its REST API.
 */
public class JiraRestBuilder extends Builder {

    private final String jiraUrl;
    private final String jiraUsername;
    private final String jiraPassword;

    // Fields in config.jelly must match the parameter names in the "DataBoundConstructor"
    @DataBoundConstructor
    public JiraRestBuilder(String jiraUrl, String jiraUsername, String jiraPassword) {
        this.jiraUrl = jiraUrl;
		this.jiraUsername = jiraUsername;
		this.jiraPassword = jiraPassword;
    }

    public String getJiraUrl() {
        return jiraUrl;
    }
    
    public String getJiraUsername() {
    	return jiraUsername;
    }
    
    public String getJiraPassword() {
    	return jiraPassword;
    }

    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) {
 
    	listener.getLogger().println("Connecting to JIRA instance at " + jiraUrl + " as " + jiraUsername + "...");
    	return true;
    }

	/**
     * Descriptor for {@link JiraRestBuilder}. Used as a singleton.
     * The class is marked as public so that it can be accessed from views.
     */
    @Extension // This indicates to Jenkins that this is an implementation of an extension point.
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        /**
         * In order to load the persisted global configuration, you have to 
         * call load() in the constructor.
         */
        public DescriptorImpl() {
            load();
        }

        /**
         * Performs on-the-fly validation of the form field 'jiraUrl'.
         *
         * @param value
         *      This parameter receives the value that the user has typed.
         * @return
         *      Indicates the outcome of the validation. This is sent to the browser.
         */
        public FormValidation doCheckJiraUrl(@QueryParameter String value)
                throws IOException, ServletException {
        	if (value.length() == 0) {
        		return FormValidation.error("Please enter a url.");
        	} else if (value.startsWith("http")) {
        		FormValidation.error("URL specified does not look like a valid url.");
        	}
             return FormValidation.ok();
        }
        
        /**
         * Performs on-the-fly validation of the form field 'jiraUsername'.
         */
        public FormValidation doCheckJiraUsername(@QueryParameter String value)
                throws IOException, ServletException {
        	if (value.length() == 0) {
        		return FormValidation.error("Please enter a username.");
        	}
             return FormValidation.ok();
        }

        /**
         * Performs on-the-fly validation of the form field 'jiraPassword'.
         */
        public FormValidation doCheckJiraPassword(@QueryParameter String value)
                throws IOException, ServletException {
        	if (value.length() == 0) {
        		return FormValidation.error("Please enter a password.");
        	}
             return FormValidation.ok();
        }
        
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            // Indicates that this builder can be used with all kinds of project types 
            return true;
        }

        /**
         * This human readable name is used in the configuration screen.
         */
        public String getDisplayName() {
            return "Interact with JIRA (REST API)";
        }

        @Override
        public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
            save();
            return super.configure(req,formData);
        }

    }
}

