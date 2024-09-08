package nl.ahmed.features.details.shared

import javax.inject.Inject
import nl.ahmed.common.kotlin.di.FragmentScope
import nl.ahmed.features.details.ui.DetailsNavigator
import nl.ahmed.templates.android.FeatureNavigator

@FragmentScope
internal class DetailsNavigatorImpl @Inject constructor() : FeatureNavigator(), DetailsNavigator
