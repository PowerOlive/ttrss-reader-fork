/*
 * Copyright (c) 2015, Nils Braden
 *
 * This file is part of ttrss-reader-fork. This program is free software; you
 * can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation;
 * either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details. You should have received a
 * copy of the GNU General Public License along with this program; If
 * not, see http://www.gnu.org/licenses/.
 */

package org.ttrssreader.gui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.ttrssreader.R;
import org.ttrssreader.controllers.Controller;
import org.ttrssreader.utils.PostMortemReportExceptionHandler;
import org.ttrssreader.utils.Utils;

import java.util.Date;

public class AboutActivity extends Activity {

	@SuppressWarnings("unused")
	private static final String TAG = AboutActivity.class.getSimpleName();

	private PostMortemReportExceptionHandler mDamageReport = new PostMortemReportExceptionHandler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(Controller.getInstance().getTheme());
		mDamageReport.initialize();

		Window w = getWindow();
		w.requestFeature(Window.FEATURE_LEFT_ICON);

		setContentView(R.layout.about);

		w.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, android.R.drawable.ic_dialog_info);

		TextView versionText = (TextView) this.findViewById(R.id.AboutActivity_VersionText);
		versionText.setText(this.getString(R.string.AboutActivity_VersionText) + " " + Utils.getAppVersionName(this));
		TextView versionCodeText = (TextView) this.findViewById(R.id.AboutActivity_VersionCodeText);
		versionCodeText.setText(this.getString(R.string.AboutActivity_VersionCodeText) + " " + Utils.getAppVersionCode(this));

		TextView licenseText = (TextView) this.findViewById(R.id.AboutActivity_LicenseText);
		licenseText.setText(this.getString(R.string.AboutActivity_LicenseText) + " " + this.getString(R.string.AboutActivity_LicenseTextValue));

		TextView urlText = (TextView) this.findViewById(R.id.AboutActivity_UrlText);
		urlText.setText(this.getString(R.string.ProjectUrl));

		TextView lastSyncText = (TextView) this.findViewById(R.id.AboutActivity_LastSyncText);
		lastSyncText.setText(this.getString(R.string.AboutActivity_LastSyncText) + " " + new Date(Controller.getInstance().getLastSync()));

		TextView thanksText = (TextView) this.findViewById(R.id.AboutActivity_ThanksText);
		thanksText.setText(this.getString(R.string.AboutActivity_ThanksTextValue));

		Button closeBtn = (Button) this.findViewById(R.id.AboutActivity_CloseBtn);
		closeBtn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				closeButtonPressed();
			}
		});

		Button donateBtn = (Button) this.findViewById(R.id.AboutActivity_DonateBtn);
		donateBtn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				donateButtonPressed();
			}
		});
	}

	@Override
	protected void onDestroy() {
		mDamageReport.restoreOriginalHandler();
		mDamageReport = null;
		super.onDestroy();
	}

	private void closeButtonPressed() {
		this.finish();
	}

	private void donateButtonPressed() {
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.DonateUrl))));
		this.finish();
	}
}
