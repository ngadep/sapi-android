package com.ngadep.fatteningcattle.progresses.edit;

import com.ngadep.fatteningcattle.BaseRepository;
import com.ngadep.fatteningcattle.models.Cow;
import com.ngadep.fatteningcattle.models.Progress;
import com.ngadep.fatteningcattle.progresses.ProgressRepository;

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

public class EditProgressPresenterTest {
    private static final String PROGRESS_ID = "lrEREsLOOKJlk875";
    private static final String COW_ID = "l4jk4i6j3KJ45tJiHg";
    private static final String PACKAGE_ID = "lHJkjUh765yTuhTR5";

    private Progress progress = new Progress(COW_ID, 1496412086216L, 210);

    private Cow cow = new Cow(PACKAGE_ID, "8687656", "male", 210, 1496412086216L);

    private EditProgressPresenter mPresenter;

    @Mock
    private EditProgressContract.View mView;

    @Mock
    private ProgressRepository mRepository;

    @Captor
    private ArgumentCaptor<BaseRepository.ModelListener<Cow>> mModelCowListenerCaptor;

    @Captor
    private ArgumentCaptor<BaseRepository.ModelListener<Progress>> mModelProgressListenerCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private void createPresenter(String progressId) {
        mPresenter = new EditProgressPresenter(progressId, COW_ID, mRepository, mView);
        verify(mView).setPresenter(mPresenter);
    }

    @Test
    public void testCreatePresenter_setsThePresenterToView() {
        createPresenter(PROGRESS_ID);
    }

    private void startPresenterNew() {
        createPresenter(null);
        mPresenter.start();
        assertTrue("is Edit Progress", mPresenter.isNewProgress());

        Mockito.verify(mRepository).getCowModelFromId(eq(COW_ID), mModelCowListenerCaptor.capture());
        mModelCowListenerCaptor.getValue().onModelChange(cow);
        assertEquals("Cow Model Not Equals", mPresenter.getCow(), cow);
    }

    @Test
    public void testStartPresenter_OnNewProgress() {
        startPresenterNew();
    }

    private void startPresenterEdit() {
        createPresenter(PROGRESS_ID);
        mPresenter.start();

        assertFalse("Progress is New", mPresenter.isNewProgress());

        Mockito.verify(mRepository).getProgressFromId(eq(PROGRESS_ID), mModelProgressListenerCaptor.capture());
        mModelProgressListenerCaptor.getValue().onModelChange(progress);
        assertEquals("Progress Model Not Equals", mPresenter.getProgress(), progress);
    }

    @Test
    public void testStartPresenter_OnEditProgress() {
        startPresenterEdit();
    }

    @Test
    public void testShowProgress_viewIsActive() {
        when(mView.isActive()).thenReturn(true);

        startPresenterEdit();

        verify(mView).setWeight(progress.getWeight());
        verify(mView).setDate(progress.getDate());
    }

    @Test
    public void testShowProgress_viewIsNotActive() {
        when(mView.isActive()).thenReturn(false);

        startPresenterEdit();

        verify(mView, never()).setWeight(progress.getWeight());
        verify(mView, never()).setDate(progress.getDate());
    }

    @Test
    public void testSaveProgress_newProgress() {
        startPresenterNew();

        mPresenter.saveProgress(220, 1496414086216L);

        verify(mRepository).saveProgress(mPresenter.getProgressEdit());
        verify(mRepository).updateCow(COW_ID, mPresenter.getCowEdit());
        verify(mView).showProgressList();
    }

    @Test
    public void testSaveProgress_editProgress() {
        startPresenterEdit();

        mPresenter.saveProgress(220, 1496414086216L);

        verify(mRepository).saveProgress(PROGRESS_ID, mPresenter.getProgressEdit());
        verify(mView).showProgressList();
    }

    @Test
    public void testCleanupRepository() {
        createPresenter(null);
        mPresenter.cleanup();
        verify(mRepository).cleanup();
    }
}