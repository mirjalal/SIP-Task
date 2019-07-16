package com.talmir.sip.task.githubpublicrepositories.di.utils

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Custom annotation class which used to
 * make [ViewModel] instances where constructor
 * parameters needed.
 */
@Target(AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
