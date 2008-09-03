package org.mifos.core;

public class AppInfo {

	private String svnRevision;
	private String buildTag;

	public String getSvnRevision() {
		return svnRevision;
	}
	public void setSvnRevision(String svnRevision) {
		this.svnRevision = svnRevision;
	}
	public String getBuildTag() {
		return buildTag;
	}
	public void setBuildTag(String buildNumber) {
		this.buildTag = buildNumber;
	}
	
}
