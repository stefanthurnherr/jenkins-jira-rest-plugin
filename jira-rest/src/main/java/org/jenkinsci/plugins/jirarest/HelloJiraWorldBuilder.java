package org.jenkinsci.plugins.jirarest;

import hudson.Extension;
import hudson.Launcher;
import hudson.Util;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import hudson.util.FormValidation;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import java.io.IOException;


public class HelloJiraWorldBuilder extends Builder {
    private final String jqlSearch;
    private final String workflowActionName;
    private final String comment;

    @DataBoundConstructor
    public HelloJiraWorldBuilder(String jqlSearch, String workflowActionName, String comment) {
        this.jqlSearch = Util.fixEmptyAndTrim(jqlSearch);
        this.workflowActionName = Util.fixEmptyAndTrim(workflowActionName);
        this.comment = Util.fixEmptyAndTrim(comment);
    }

    /**
     * @return the jql
     */
    public String getJqlSearch() {
        return jqlSearch;
    }

    /**
     * @return the workflowActionName
     */
    public String getWorkflowActionName() {
        return workflowActionName;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Performs the actual update based on job configuration.
     */
    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws InterruptedException, IOException {
//        String realComment = Util.fixEmptyAndTrim(build.getEnvironment(listener).expand(comment));
//        String realJql = Util.fixEmptyAndTrim(build.getEnvironment(listener).expand(jqlSearch));
// 
//        JiraSite site = JiraSite.get(build.getProject());
//
//        if (site == null) {
//            listener.getLogger().println(Messages.Updater_NoJiraSite());
//            build.setResult(Result.FAILURE);
//            return true;
//        }
//
//        if (StringUtils.isNotEmpty(workflowActionName)) {
//            listener.getLogger().println(Messages.JiraIssueUpdateBuilder_UpdatingWithAction(workflowActionName));
//        }

        listener.getLogger().println("[JIRA-REST] performing build action...");

//        try {
//            if (!site.progressMatchingIssues(realJql, workflowActionName, realComment, listener.getLogger())) {
//                listener.getLogger().println(Messages.JiraIssueUpdateBuilder_SomeIssuesFailed());
//                build.setResult(Result.UNSTABLE);
//            }
//        } catch (ServiceException e) {
//            listener.getLogger().println(Messages.JiraIssueUpdateBuilder_Failed());
//            e.printStackTrace(listener.getLogger());
//            return false;
//        }

        return true;
    }

    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl) super.getDescriptor();
    }

    /**
     * Descriptor for {@link HelloJiraWorldBuilder}.
     */
    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {
        /**
         * Performs on-the-fly validation of the form field 'Jql'.
         *
         * @param value This parameter receives the value that the user has typed.
         * @return Indicates the outcome of the validation. This is sent to the browser.
         */
        public FormValidation doCheckJqlSearch(@QueryParameter String value) throws IOException {
            if (value.length() == 0) {
                return FormValidation.error("Messages.JiraIssueUpdateBuilder_NoJqlSearch()");
            }

            return FormValidation.ok();
        }

        public FormValidation doCheckWorkflowActionName(@QueryParameter String value) {
            if (Util.fixNull(value).trim().length() == 0) {
                return FormValidation.warning("Messages.JiraIssueUpdateBuilder_NoWorkflowAction()");
            }

            return FormValidation.ok();
        }

        @SuppressWarnings("rawtypes")
		public boolean isApplicable(Class<? extends AbstractProject> klass) {
            return true;
        }

        /**
         * This human readable name is used in the configuration screen.
         */
        public String getDisplayName() {
            return "Messages.JiraIssueUpdateBuilder_DisplayName()";
        }
    }
}
