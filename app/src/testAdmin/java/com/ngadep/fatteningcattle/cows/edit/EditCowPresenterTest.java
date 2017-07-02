package com.ngadep.fatteningcattle.cows.edit;

import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.cows.CowRepository;
import com.ngadep.fatteningcattle.models.Cow;

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

public class EditCowPresenterTest {
    private static final String COW_ID = "l4jk4i6j3KJ45tJiHg";
    private static final String PACKAGE_ID = "lHJkjUh765yTuhTR5";

    private Cow cow = new Cow(PACKAGE_ID, "8687656", "male", 210, 1496412086216L);

    private EditCowPresenter mPresenter;

    @Mock
    private EditCowContract.View mView;

    @Mock
    private CowRepository mRepository;

    @Captor
    private ArgumentCaptor<BaseRepository.ModelListener<Cow>> mModelCowListenerCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private void createPresenter(String cowId) {
        mPresenter = new EditCowPresenter(cowId, PACKAGE_ID, mRepository, mView);
        verify(mView).setPresenter(mPresenter);
    }

    @Test
    public void testCreatePresenter_setsThePresenterToView() {
        createPresenter(COW_ID);
    }

    private void startPresenterNew() {
        createPresenter(null);
        mPresenter.start();
        assertTrue("is Edit Cow", mPresenter.isNewCow());
    }

    @Test
    public void testStartPresenter_OnNewCow() {
        startPresenterNew();
    }

    private void startPresenterEdit() {
        createPresenter(COW_ID);
        mPresenter.start();

        assertFalse("Cow is New", mPresenter.isNewCow());

        Mockito.verify(mRepository).getCowFromId(eq(COW_ID), mModelCowListenerCaptor.capture());
        mModelCowListenerCaptor.getValue().onModelChange(cow);
        assertEquals("Cow Model Not Equals", mPresenter.getCow(), cow);
    }

    @Test
    public void testStartPresenter_OnEditCow() {
        startPresenterEdit();
    }

    @Test
    public void testShowCow_viewIsActive() {
        when(mView.isActive()).thenReturn(true);

        startPresenterEdit();

        verify(mView).setEarTag(cow.getEar_tag());
        verify(mView).setSex(cow.getSex());
        verify(mView).setWeight(cow.getWeight());
        verify(mView).setDate(cow.getDate());
        verify(mView).hideWeightAndDate();
    }

    @Test
    public void testShowCow_viewIsNotActive() {
        when(mView.isActive()).thenReturn(false);

        startPresenterEdit();

        verify(mView, never()).setEarTag(cow.getEar_tag());
        verify(mView, never()).setSex(cow.getSex());
        verify(mView, never()).setWeight(cow.getWeight());
        verify(mView, never()).setDate(cow.getDate());
        verify(mView, never()).hideWeightAndDate();
    }

    @Test
    public void testSaveCow_newCow() {
        startPresenterNew();

        mPresenter.saveCow("8687656", "male", 210, 1496412086216L);

        verify(mRepository).saveCow(mPresenter.getCowEdit());
        verify(mView).showCowList();
    }

    @Test
    public void testSaveCow_editCow() {
        startPresenterEdit();

        mPresenter.saveCow("8687656", "male", 210, 1496412086216L);

        verify(mRepository).saveCow(COW_ID, mPresenter.getCowEdit());
        verify(mView).showCowList();
    }

    @Test
    public void testCleanupRepository() {
        createPresenter(null);
        mPresenter.cleanup();
        verify(mRepository).cleanup();
    }
}