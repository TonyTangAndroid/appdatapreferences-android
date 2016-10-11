/**
 * Copyright 2013 Google Inc. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.drive.appdatapreferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Contains several utility functions.
 *
 * @author jbd@google.com (Burcu Dogan)
 */
public class Utils {

	/**
	 * Reads an input stream line by line and converts it into String.
	 *
	 * @param inputStream
	 * @throws IOException
	 */
	public static String fromInputStreamtoString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = null;
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}
		return stringBuilder.toString();
	}

	/**
	 * Clears the given {@code SharedPreferences} values and puts each
	 * value on the given map to the preferences.
	 *
	 * @param prefs
	 * @param changes
	 */
	public static void replaceValues(
			SharedPreferences prefs, Map<String, Object> changes) {
		if (changes == null) {
			return;
		}

		// clears the existing values in the preferences
		// instance and replaces them with what's on changes map.
		Editor editor = prefs.edit().clear();
		for (String key : changes.keySet()) {
			Object value = changes.get(key);
			if (value instanceof Boolean) {
				editor.putBoolean(key, (Boolean) value);
			} else if (value instanceof Double) {
				editor.putFloat(key, Float.valueOf(value + ""));
			} else if (value instanceof Integer) {
				editor.putInt(key, (Integer) value);
			} else if (value instanceof Long) { // Can value be Long?
				editor.putLong(key, (Long) value);
			} else if (value instanceof String) {
				editor.putString(key, (String) value);
			}
		}
		editor.commit();
	}

}
