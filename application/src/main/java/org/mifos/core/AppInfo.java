package org.mifos.core;

public class AppInfo {

	private String svnRevision;
	private String buildTag;
	private String buildId;

	public String getBuildId() {
		return buildId;
	}
	public void setBuildId(String buildId) {
		this.buildId = buildId;
	}
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
