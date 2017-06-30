package com.ngadep.fatteningcattle.packages.edit;

import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.packages.PackageRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ngadep.fatteningcattle.models.Package;

public class EditPackagePresenterTest {
    private static final String PACKAGE_ID = "lHJkjUh765yTuhTR5";
    private static final String USER_ID = "l4jk4i6j3KJ565JiHg";

    private Package pkg = new Package(USER_ID, "PACKAGE01", "INDONESIA", 5, true);

    private EditPackagePresenter mPresenter;

    @Mock
    private EditPackageContract.View mView;

    @Mock
    private PackageRepository mRepository;

    @Captor
    private ArgumentCaptor<BaseRepository.ModelListener<Package>> mModelPackageListenerCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private void createPresenter(String packageId) {
        mPresenter = new EditPackagePresenter(packageId, USER_ID, mRepository, mView);
        verify(mView).setPresenter(mPresenter);
    }

    @Test
    public void testCreatePresenter_setsThePresenterToView() {
        createPresenter(PACKAGE_ID);
    }

    private void startPresenterNew() {
        createPresenter(null);
        mPresenter.start();
        assertTrue("is Edit Package", mPresenter.isNewPackage());
    }

    @Test
    public void testStartPresenter_OnNewPackage() {
        startPresenterNew();
    }

    private void startPresenterEdit() {
        createPresenter(PACKAGE_ID);
        mPresenter.start();

        assertFalse("Package is New", mPresenter.isNewPackage());

        Mockito.verify(mRepository).getPackageFromId(eq(PACKAGE_ID), mModelPackageListenerCaptor.capture());
        mModelPackageListenerCaptor.getValue().onModelChange(pkg);
        assertEquals("Package Model Not Equals", mPresenter.getPackage(), pkg);
    }

    @Test
    public void testStartPresenter_OnEditPackage() {
        startPresenterEdit();
    }

    @Test
    public void testShowPackage_viewIsActive() {
        when(mView.isActive()).thenReturn(true);

        startPresenterEdit();

        verify(mView).setName(pkg.getName());
        verify(mView).setLocation(pkg.getLocation());
        verify(mView).setType(pkg.getType());
        verify(mView).setActive(pkg.isActive());
    }

    @Test
    public void testShowPackage_viewIsNotActive() {
        when(mView.isActive()).thenReturn(false);

        startPresenterEdit();

        verify(mView, never()).setName(pkg.getName());
        verify(mView, never()).setLocation(pkg.getLocation());
        verify(mView, never()).setType(pkg.getType());
        verify(mView, never()).setActive(pkg.isActive());
    }

    @Test
    public void testSavePackage_newPackage() {
        startPresenterNew();

        mPresenter.savePackage("PACKAGE01", "INDONESIA", 5, true);

        verify(mRepository).savePackage(mPresenter.getEditPackage());
        verify(mView).showPackageList();
    }

    @Test
    public void testSavePackage_editPackage() {
        startPresenterEdit();

        mPresenter.savePackage("PACKAGE01", "INDONESIA", 5, true);

        verify(mRepository).savePackage(PACKAGE_ID, mPresenter.getEditPackage());
        verify(mView).showPackageList();
    }

    @Test
    public void testCleanupRepository() {
        createPresenter(null);
        mPresenter.cleanup();
        verify(mRepository).cleanup();
    }
}