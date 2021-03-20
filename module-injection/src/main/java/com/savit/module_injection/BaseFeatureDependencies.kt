package com.savit.module_injection

interface BaseFeatureDependencies {
    val dependencyHolder: BaseDependencyHolder<out BaseFeatureDependencies>
}

interface BaseFeatureAPI

interface ComponentHolder<A : BaseFeatureAPI, D : BaseFeatureDependencies> {
    var dependencyProvider: (() -> D)?
    fun get(): A
}