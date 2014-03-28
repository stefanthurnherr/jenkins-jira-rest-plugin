package org.jenkinsci.plugins.jira_rest.jiraclient;

import org.jenkinsci.plugins.jira_rest.jiraclient.implrcarz.RcarzJiraClient;

public class JiraClientFactory {
	
	public static JiraClient create(String url, String username, String password) {
		return new RcarzJiraClient(url, username, password);
	}
}
