package com.talkortell.bbs.base.common.service;

import java.util.List;

import com.talkortell.bbs.base.common.page.TotPage;

@FunctionalInterface
public interface BatchServiceSolveInvoker<P, R> {
	List<R> solve(P request, TotPage page);
}
