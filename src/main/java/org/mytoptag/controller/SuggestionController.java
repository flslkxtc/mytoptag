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

package org.mytoptag.controller;

import org.mytoptag.model.dto.ListResponseEntity;
import org.mytoptag.model.dto.TagSuggestion;
import org.mytoptag.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * Tag suggestion controller.
 */
@RestController
@CrossOrigin
@RequestMapping(
    value = "/suggestion",
    produces = {"application/json"}
)
public class SuggestionController {

  private SuggestionService suggestionService;

  /**
   * Ctor.
   *
   * @param suggestionService {@link SuggestionService}
   */
  @Autowired
  public SuggestionController(final SuggestionService suggestionService) {
    this.suggestionService = suggestionService;
  }


  /**
   * Get most relevant tags for current user's.
   *
   * @param search list of category strings
   * @return list of {@link TagSuggestion}
   */
  @RequestMapping(
      value = "/{search}",
      method = RequestMethod.GET
  )
  public ListResponseEntity getRecommendations(@PathVariable("search") final List<String> search) {
    return new ListResponseEntity(suggestionService.getRecommendations(new LinkedHashSet<>(search)));
  }

}
