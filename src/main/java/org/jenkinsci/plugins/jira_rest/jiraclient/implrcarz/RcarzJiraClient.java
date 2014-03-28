package org.jenkinsci.plugins.jira_rest.jiraclient.implrcarz;

import org.jenkinsci.plugins.jira_rest.jiraclient.JiraClient;

import net.rcarz.jiraclient.BasicCredentials;

public class RcarzJiraClient implements JiraClient {
	
	private final net.rcarz.jiraclient.JiraClient jiraClient;
	
	public RcarzJiraClient(String url, String username, String password) {
		final BasicCredentials credentials = new BasicCredentials(username, password);
		jiraClient = new net.rcarz.jiraclient.JiraClient(url, credentials);
	}
	
	

}
