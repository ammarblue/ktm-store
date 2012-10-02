package org.ktm.tags.auth;

public interface Printable {

    public void print();

    public void print(java.io.Writer w);

    public void print(java.io.Writer w, String indent);
}