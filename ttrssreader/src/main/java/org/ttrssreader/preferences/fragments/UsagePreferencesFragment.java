package org.ttrssreader.preferences.fragments;

import android.os.Bundle;

import org.ttrssreader.R;

import androidx.preference.PreferenceFragmentCompat;

public class UsagePreferencesFragment extends PreferenceFragmentCompat {

	@SuppressWarnings("unused")
	private static final String TAG = UsagePreferencesFragment.class.getSimpleName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
		addPreferencesFromResource(R.xml.prefs_usage);
	}
}
