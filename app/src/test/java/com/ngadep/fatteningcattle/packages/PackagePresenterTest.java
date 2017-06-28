package com.ngadep.fatteningcattle.packages;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PackagePresenterTest {

    // random user id
    private static final java.lang.String USER_ID = "lkj234jk9j9";
    private PackagePresenter mPresenter;

    @Mock
    private PackageContract.View mView;

    @Mock
    private PackageRepository mRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePresenter_setsThePresenterToView() {
        mPresenter = new PackagePresenter(null, mView, mRepository);
        verify(mView).setPresenter(mPresenter);
    }

    @Test
    public void testStartPresenter_newPackage() {
        mPresenter = new PackagePresenter(null, mView, mRepository);
        mPresenter.start();

        when(mRepository.getUid()).thenReturn(USER_ID);

        verify(mRepository).getUid();

        verify(mRepository).getPackagesFromUserId(null);
        verify(mView).getPackages(mRepository.getPackagesFromUserId(null));
    }



}