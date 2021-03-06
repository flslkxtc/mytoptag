/*
 * Copyright (c) 2018 Stanislav Myachenkov
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package org.mytoptag.service;

import org.mytoptag.model.Category;
import org.mytoptag.model.dto.TagSuggestion;
import org.mytoptag.model.dto.request.CategorizedTag;

import java.util.List;


/**
 * Tag category service.
 */
public interface CategoryService {

  /**
   * Save tags to categories mapping.
   *
   * @param categorizedTags list of {@link CategorizedTag}
   */
  void save(List<CategorizedTag> categorizedTags);

  /**
   * Clear category data.
   */
  void clear();

  /**
   * Get {@link Category} by name.
   *
   * @param title title of category
   * @return list of all in category {@link TagSuggestion}
   */
  List<TagSuggestion> getCategoryTags(String title);

}
