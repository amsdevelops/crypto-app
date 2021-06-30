package com.devsoldatenkov.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue
import com.devsoldatenkov.lint.WrongMatchParentDetector.Companion.MATCH_PARENT_CONSTRAINT_ISSUE

@Suppress("UnstableApiUsage")
class MyIssueRegistry : IssueRegistry() {
    override val issues: List<Issue> = listOf(MATCH_PARENT_CONSTRAINT_ISSUE)
}