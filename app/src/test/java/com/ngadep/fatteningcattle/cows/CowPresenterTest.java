package com.ngadep.fatteningcattle.cows;

import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.models.Package;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class CowPresenterTest {
    // random package id
    private static final java.lang.String PACKAGE_ID = "SDFsfs4534Der";
    private static final String PRICE_KEY = "price";
    private static final Long PRICE_VALUE = 49300L;

    private CowPresenter mPresenter;

    @Mock
    private CowContract.View mView;

    @Mock
    private CowRepository mRepository;

    @Captor
    private ArgumentCaptor<BaseRepository.ModelListener<Package>> mModelPackageListenerCaptor;

    @Captor
    private ArgumentCaptor<BaseRepository.SettingListener<Long>> mPriceSettingListenerCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePresenter_setsThePresenterToView() {
        mPresenter = new CowPresenter(mView, mRepository, "");
        verify(mView).setPresenter(mPresenter);
    }

    @Test
    public void testStartPresenter() {
        mPresenter = new CowPresenter(mView, mRepository, PACKAGE_ID);
        mPresenter.start();
        Package pkg = new Package("jkli67yiu", "PACKAGE01", "INDONESIA", 5, true);

        Mockito.verify(mRepository).getPackageFromId(eq(PACKAGE_ID), mModelPackageListenerCaptor.capture());
        mModelPackageListenerCaptor.getValue().onModelChange(pkg);
        assertEquals("Package Not Equals", mPresenter.getPackage(), pkg);
        verify(mView).notifyPackageChange(pkg);

        Mockito.verify(mRepository).getSetting(eq(PRICE_KEY), mPriceSettingListenerCaptor.capture());
        mPriceSettingListenerCaptor.getValue().onValueChange(PRICE_VALUE);
        assertEquals("Price Value Not Equals", mPresenter.getPricePerKg(), PRICE_VALUE);
        verify(mView).notifyPriceChange();

        verify(mRepository).getPackageCowFromId(PACKAGE_ID);
        verify(mView).getAllPackageCow(mRepository.getPackageCowFromId(PACKAGE_ID));
    }

}