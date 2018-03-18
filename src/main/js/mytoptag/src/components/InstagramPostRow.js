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
import React, { Component } from 'react';
import PropTypes from 'prop-types';
import '../css/InstagramPostRow.css';

const INSTAGRAM_POST_URL='https://www.instagram.com/p/';


// todo css, formatting, links from tags
class InstagramPostRow extends Component {
  render() {
    const postLink = INSTAGRAM_POST_URL + this.props.shortCode;
    var tags;
    this.props.tags.forEach(function(item, i, arr) {
          tags += '#' + item + ' '
      })
    return (
      <div className="component-instagram-post-row">
        <div>
          likes: {this.props.likes} &nbsp;
          <a href={this.props.previewLink}>preview</a> &nbsp;
          <a href={postLink}>post</a> &nbsp;
          {tags}
        </div>
      </div>
    );
  }
}

InstagramPostRow.propTypes = {
  shortCode: PropTypes.String,
  previewLink: PropTypes.String,
  tags: PropTypes.array,
  likes: PropTypes.number
};


export default InstagramPostRow;