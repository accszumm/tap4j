/*
 * The MIT License
 * 
 * Copyright (c) 2010 tap4j team (see AUTHORS)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.tap4j.tokens;

import org.tap4j.error.Mark;

public class TestResultToken extends AbstractToken implements CommentableToken,
        DiagnosticableToken {
    public enum Status {
        OK, NOT_OK
    }

    private final Status status;
    private final int number;
    private final String description;
    private String comment;
    private final Skip skip;
    private final Todo todo;
    private final StringBuilder diagnostics;

    public TestResultToken(Status status, int number, String description,
            String comment, Mark startMark, Mark endMark) {
        this(status, number, description, comment, null, null, startMark, endMark);
    }

    /**
     * @param status
     * @param number
     * @param description
     * @param comment
     * @param skip
     * @param todo
     * @param startMark
     * @param endMark
     */
    public TestResultToken(Status status, int number, String description,
            String comment, Skip skip, Todo todo, Mark startMark, Mark endMark) {
        super(startMark, endMark);
        this.status = status;
        this.number = number;
        this.description = description;
        this.comment = comment;
        this.skip = skip;
        this.todo = todo;
        this.diagnostics = new StringBuilder();
    }

    public Status getStatus() {
        return status;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.tap4j.tokens.CommentableToken#addComment(java.lang.String)
     */
    public void addComment(String comment) {
        this.comment = comment;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.tap4j.tokens.CommentableToken#getComment()
     */
    public String getComment() {
        return comment;
    }

    /**
     * @return the skip
     */
    public Skip getSkip() {
        return skip;
    }

    /**
     * @return the todo
     */
    public Todo getTodo() {
        return todo;
    }

    @Override
    public ID getTokenId() {
        return ID.TestResult;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.tap4j.tokens.DiagnosticableToken#addDiagnostics(java.lang.String)
     */
    public void addDiagnostics(String text) {
        this.diagnostics.append(text);
        this.diagnostics.append('\n');
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.tap4j.tokens.DiagnosticableToken#getDiagnostics()
     */
    public String getDiagnostics() {
        return this.diagnostics.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.tap4j.tokens.AbstractToken#getArguments()
     */
    @Override
    protected String getArguments() {
        StringBuilder args = new StringBuilder();
        args.append("status=");
        args.append((this.status == TestResultToken.Status.OK ? "ok" : "not ok"));
        args.append(", number=" + number);
        args.append(", description=" + description);
        args.append(", comment=" + comment);
        args.append(", skip=" + skip);
        args.append(", todo=" + todo);
        return args.toString();
    }

}
