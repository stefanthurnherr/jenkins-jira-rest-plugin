jira-rest
=========

This Jenkins plugin will eventually integrate Jenkins to <a href="http://www.atlassian.com/software/jira/">Atlassian JIRA</a> using the REST API available since JIRA 6. The aim is to solve <a href="https://issues.jenkins-ci.org/browse/JENKINS-18166">JENKINS-18166</a>

Planned features (in no particular order):
* Add links to issue keys mentioned in commit comments
* Ability to transition issues of a specified JIRA project from one workflow status into another workflow status
* Set version of all issues of a specified JIRA project and in a specified workflow status to a specified version
* Get list of transitioned issues
