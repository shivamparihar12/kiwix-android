/*
 * Kiwix Android
 * Copyright (c) 2020 Kiwix <android.kiwix.org>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.kiwix.kiwixmobile.custom.main

import android.content.Intent
import android.os.Bundle
import android.view.ActionMode
import org.kiwix.kiwixmobile.core.base.BaseFragmentActivityExtensions
import org.kiwix.kiwixmobile.core.di.components.CoreComponent
import org.kiwix.kiwixmobile.core.main.CoreNewNavigationActivity
import org.kiwix.kiwixmobile.custom.R
import org.kiwix.kiwixmobile.custom.customActivityComponent

class CustomNewNavigationActivity : CoreNewNavigationActivity() {
  override fun injection(coreComponent: CoreComponent) {
    customActivityComponent.inject(this)
  }

  override fun onActionModeStarted(mode: ActionMode) {
    super.onActionModeStarted(mode)
    supportFragmentManager.fragments.filterIsInstance<BaseFragmentActivityExtensions>().forEach {
      it.onActionModeStarted(mode, this)
    }
  }

  override fun onActionModeFinished(mode: ActionMode) {
    super.onActionModeFinished(mode)
    supportFragmentManager.fragments.filterIsInstance<BaseFragmentActivityExtensions>().forEach {
      it.onActionModeFinished(mode, this)
    }
  }

  override fun onBackPressed() {
    supportFragmentManager.fragments.filterIsInstance<BaseFragmentActivityExtensions>().forEach {
      if (it.onBackPressed(this) == BaseFragmentActivityExtensions.Super.ShouldCall) {
        super.onBackPressed()
      }
    }
  }

  override fun onNewIntent(intent: Intent) {
    super.onNewIntent(intent)
    supportFragmentManager.fragments.filterIsInstance<BaseFragmentActivityExtensions>().forEach {
      it.onNewIntent(intent, this)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    if (savedInstanceState != null) {
      return
    }
    supportFragmentManager.beginTransaction()
      .add(R.id.fragment_custom_app_container, CustomReaderFragment()).commit()
  }
}
