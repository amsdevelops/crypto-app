package com.devsoldatenkov.lint

import com.android.resources.ResourceFolderType
import com.android.tools.lint.detector.api.*
import org.w3c.dom.Element

@Suppress("UnstableApiUsage")
class WrongMatchParentDetector : ResourceXmlDetector() {
    private val tags = setOf(
        "layout_width",
        "layout_height"
    )
    private val constraintLayoutAttrs = setOf(
        "app:layout_constraintLeft_toLeftOf",
        "app:layout_constraintRight_toRightOf",
        "app:layout_constraintTop_toTopOf",
        "app:layout_constraintBottom_toTopOf",
        "app:layout_constraintTop_toBottomOf",
        "app:layout_constraintBottom_toBottomOf",
        "app:layout_constraintLeft_toRightOf",
        "app:layout_constraintRight_toLeftOf",
        "app:layout_constraintStart_toEndOf",
        "app:layout_constraintEnd_toStartOf"
    )

    override fun getApplicableElements(): Collection<String>? = ALL

    override fun appliesTo(folderType: ResourceFolderType): Boolean = ResourceFolderType.LAYOUT == folderType

    override fun visitElement(context: XmlContext, element: Element) {
        if(!constraintLayoutAttrs.any { element.hasAttribute(it) })  return

        tags.forEach {
            val node = element.getAttributeNS(NAMESPACE, it)
            if (node == WRONG_MATCH_PARENT_IN_CONSTRAINT_LAYOUT) {
                context.report(
                    issue = MATCH_PARENT_CONSTRAINT_ISSUE,
                    location = context.getLocation(element),
                    message = DESC,
                    quickfixData = createFix()
                )
            }
        }

    }

    private fun createFix(): LintFix = LintFix.create()
        .replace()
        .with(RIGHT_MATCH_PARENT_IN_CONSTRAINT_LAYOUT)
        .build()

    companion object {
        private const val WRONG_MATCH_PARENT_IN_CONSTRAINT_LAYOUT = "match_parent"
        private const val RIGHT_MATCH_PARENT_IN_CONSTRAINT_LAYOUT = "0dp"
        private const val NAMESPACE = "http://schemas.android.com/apk/res/android"

        private const val DESC = "Match parent attribute in Constraint layout should be 0dp"
        private const val EXPLANATION = "Match parent attribute in Constraint layout should be 0dp"

        val MATCH_PARENT_CONSTRAINT_ISSUE = Issue.create(
            id = "WrongMatchParentInConstraintLayout",
            briefDescription = DESC,
            explanation = EXPLANATION,
            category = Category.CORRECTNESS,
            priority = 5,
            severity = Severity.WARNING,
            implementation = Implementation(
                WrongMatchParentDetector::class.java,
                Scope.RESOURCE_FILE_SCOPE
            )
        )
    }
}