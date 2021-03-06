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

package org.mytoptag.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.mytoptag.model.dto.InstagramSearch;
import org.mytoptag.model.dto.InstagramTagSearchResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Custom deserializer for instagram profile and tag search response.
 */
public class InstagramSearchDeserializer extends StdDeserializer<InstagramSearch> {

  protected InstagramSearchDeserializer() {
    this(null);
  }

  protected InstagramSearchDeserializer(final Class<?> vc) {
    super(vc);
  }

  @Override
  public InstagramSearch deserialize(final JsonParser parser, final DeserializationContext context)
      throws IOException {
    final JsonNode node = parser.getCodec().readTree(parser);
    final List<InstagramTagSearchResult> tags = parseTags(node);
    final List users = new ArrayList();
    return new InstagramSearch(users, tags);
  }

  private List<InstagramTagSearchResult> parseTags(final JsonNode node) {
    final List<InstagramTagSearchResult> result = new ArrayList<>();
    for (Iterator<JsonNode> it = node.get("hashtags").elements(); it.hasNext();) {
      final JsonNode tagNode = it.next().get("hashtag");
      final String name = tagNode.get("name").asText();
      final Long igId = tagNode.get("id").asLong();
      final Long count = tagNode.get("media_count").longValue();
      result.add(new InstagramTagSearchResult(name, igId, count));
    }
    return result;
  }
}
