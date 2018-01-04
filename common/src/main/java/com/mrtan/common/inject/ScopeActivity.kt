package com.mrtan.common.inject

import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.RUNTIME

/**
 * @author mrtan on 17-3-14.
 */
@Scope
@Retention(RUNTIME)
annotation class ScopeActivity
