package com.app.mytask.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserResponse {
    @SerializedName("id")
    @Expose
    val id: Int? = null

    @SerializedName("node_id")
    @Expose
    val nodeId: String? = null

    @SerializedName("name")
    @Expose
    val name: String? = null

    @SerializedName("full_name")
    @Expose
    private val fullName: String? = null

    @SerializedName("private")
    @Expose
    private val _private: Boolean? = null

    @SerializedName("owner")
    @Expose
    val owner: Owner? = null

    @SerializedName("html_url")
    @Expose
    private val htmlUrl: String? = null

    @SerializedName("description")
    @Expose
    val description: String? = null

    @SerializedName("fork")
    @Expose
    private val fork: Boolean? = null

    @SerializedName("url")
    @Expose
    private val url: String? = null

    @SerializedName("forks_url")
    @Expose
    private val forksUrl: String? = null

    @SerializedName("keys_url")
    @Expose
    private val keysUrl: String? = null

    @SerializedName("collaborators_url")
    @Expose
    private val collaboratorsUrl: String? = null

    @SerializedName("teams_url")
    @Expose
    private val teamsUrl: String? = null

    @SerializedName("hooks_url")
    @Expose
    private val hooksUrl: String? = null

    @SerializedName("issue_events_url")
    @Expose
    private val issueEventsUrl: String? = null

    @SerializedName("events_url")
    @Expose
    private val eventsUrl: String? = null

    @SerializedName("assignees_url")
    @Expose
    private val assigneesUrl: String? = null

    @SerializedName("branches_url")
    @Expose
    private val branchesUrl: String? = null

    @SerializedName("tags_url")
    @Expose
    private val tagsUrl: String? = null

    @SerializedName("blobs_url")
    @Expose
    private val blobsUrl: String? = null

    @SerializedName("git_tags_url")
    @Expose
    private val gitTagsUrl: String? = null

    @SerializedName("git_refs_url")
    @Expose
    private val gitRefsUrl: String? = null

    @SerializedName("trees_url")
    @Expose
    private val treesUrl: String? = null

    @SerializedName("statuses_url")
    @Expose
    private val statusesUrl: String? = null

    @SerializedName("languages_url")
    @Expose
    private val languagesUrl: String? = null

    @SerializedName("stargazers_url")
    @Expose
    private val stargazersUrl: String? = null

    @SerializedName("contributors_url")
    @Expose
    private val contributorsUrl: String? = null

    @SerializedName("subscribers_url")
    @Expose
    private val subscribersUrl: String? = null

    @SerializedName("subscription_url")
    @Expose
    private val subscriptionUrl: String? = null

    @SerializedName("commits_url")
    @Expose
    private val commitsUrl: String? = null

    @SerializedName("git_commits_url")
    @Expose
    private val gitCommitsUrl: String? = null

    @SerializedName("comments_url")
    @Expose
    private val commentsUrl: String? = null

    @SerializedName("issue_comment_url")
    @Expose
    private val issueCommentUrl: String? = null

    @SerializedName("contents_url")
    @Expose
    private val contentsUrl: String? = null

    @SerializedName("compare_url")
    @Expose
    private val compareUrl: String? = null

    @SerializedName("merges_url")
    @Expose
    private val mergesUrl: String? = null

    @SerializedName("archive_url")
    @Expose
    private val archiveUrl: String? = null

    @SerializedName("downloads_url")
    @Expose
    private val downloadsUrl: String? = null

    @SerializedName("issues_url")
    @Expose
    private val issuesUrl: String? = null

    @SerializedName("pulls_url")
    @Expose
    private val pullsUrl: String? = null

    @SerializedName("milestones_url")
    @Expose
    private val milestonesUrl: String? = null

    @SerializedName("notifications_url")
    @Expose
    private val notificationsUrl: String? = null

    @SerializedName("labels_url")
    @Expose
    private val labelsUrl: String? = null

    @SerializedName("releases_url")
    @Expose
    private val releasesUrl: String? = null

    @SerializedName("deployments_url")
    @Expose
    private val deploymentsUrl: String? = null
}