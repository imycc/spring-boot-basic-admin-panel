package com.imyc.SBAP.factories.dummy.base;

import com.imyc.SBAP.Base.dto.DatatableServerSideConfig;
import com.imyc.SBAP.factories.dummy.DummyFactory;
import org.springframework.lang.Nullable;

public class DummyDatatableServerSideConfigFactory implements DummyFactory<DatatableServerSideConfig> {

    private DatatableServerSideConfig dummyDatatableServerSideConfig;

    public DummyDatatableServerSideConfigFactory(int draw, int start, int length, @Nullable String keyword) {
        this.dummyDatatableServerSideConfig = new DatatableServerSideConfig();
        this.dummyDatatableServerSideConfig
                .setDraw(draw)
                .setStart(start)
                .setLength(length)
                .setKeyword(keyword);
    }

    @Override
    public DatatableServerSideConfig make() {
        return dummyDatatableServerSideConfig;
    }
}
