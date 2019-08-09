package com.talkortell.bbs.base.common.service;

@FunctionalInterface
public interface SingleServiceSolveInvoker<P, R> {
	R solve(P request);
}
